package com.yazlab.academichub.service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.PublicationCriteria;
import com.yazlab.academichub.entities.candidateDocuments.CandidateArticle;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.Table3ActionRepository;
import com.yazlab.academichub.response.ApplicationValidationResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ApplicationValidationService {

    private final ApplicationRepository applicationRepository;
    private final Table3ActionRepository table3ActionRepository;

    private Map<String, List<String>> categoryGroups;

    @PostConstruct
    public void init() {
        categoryGroups = new HashMap<>();
        List<String> actionNames = table3ActionRepository.findAllActionNames();
        for (String action : actionNames) {
            categoryGroups.put(action, parseCategoryRange(action));
        }
    }

    private List<String> parseCategoryRange(String action) {
        if (!action.contains("-")) return List.of(action);
        try {
            String[] parts = action.split("-");
            String prefix = parts[0].replaceAll("[0-9]", "");
            int start = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
            int end = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));

            List<String> result = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                result.add(prefix + i);
            }
            return result;
        } catch (Exception e) {
            return List.of(action);
        }
    }

    public boolean isApplicationValid(Long applicationId) {
        Application application = applicationRepository.findWithArticlesAndAuthorsByApplicationId(applicationId);
        if (application == null) throw new RuntimeException("Başvuru bulunamadı: ID = " + applicationId);
    
        List<CandidateArticle> availableArticles = new ArrayList<>();
        for (CandidateArticle article : application.getArticles()) {
            String category = article.getArticleCategory();
            if (category != null && category.toUpperCase().startsWith("A")) {
                availableArticles.add(article);
            }
        }
    
        String position = application.getJobOffer().getPosition().getPositionName();
        String facultyGroup = application.getJobOffer().getDepartment().getFaculty().getFacultyGroup().getFacultyGroupName();
    
        System.out.println("=== BAŞVURU UYGUNLUK KONTROLÜ (DİNAMİK + TÜKETİMLİ) ===");
        System.out.println("Pozisyon: " + position + " | Fakülte Grubu: " + facultyGroup);
    
        boolean allCriteriaMet = true;
    
        List<PublicationCriteria> sortedCriterias = application.getJobOffer().getPublicationCriterias().stream()
                .filter(criteria ->
                        position.equalsIgnoreCase(criteria.getTable3Action().getPosition().getPositionName()) &&
                        facultyGroup.equalsIgnoreCase(criteria.getTable3Action().getFacultyGroup().getFacultyGroupName()) &&
                        (criteria.getTable3Action().getActionName().toUpperCase().startsWith("A") ||
                         criteria.getTable3Action().getActionName().equalsIgnoreCase("Başlıca Yazar"))
                )
                .sorted(Comparator.comparing(c -> c.getTable3Action().getActionName()))
                .toList();
    
        for (PublicationCriteria criteria : sortedCriterias) {
            String category = criteria.getTable3Action().getActionName();
            int required = criteria.getArticleCount();
            int actual = 0;
            if (required == 0) continue;
    
            List<CandidateArticle> consumed = new ArrayList<>();
    
            if (category.equalsIgnoreCase("Başlıca Yazar")) {
                // Tüm makalelerden kontrol et
                for (CandidateArticle article : application.getArticles()) {
                    if (article.getAuthors() != null &&
                        article.getAuthors().stream().anyMatch(a -> a.getAuthorType() != null && a.getAuthorType().getId() == 1) &&
                        !consumed.contains(article)) {
                        consumed.add(article);
                        if (consumed.size() == required) break;
                    }
                }
            } else {
                List<String> subCategories = categoryGroups.getOrDefault(category, List.of(category));
                List<String> sortedSubs = new ArrayList<>(subCategories);
                Collections.sort(sortedSubs);
    
                for (String sub : sortedSubs) {
                    for (CandidateArticle article : availableArticles) {
                        if (sub.equalsIgnoreCase(article.getArticleCategory()) && !consumed.contains(article)) {
                            consumed.add(article);
                            if (consumed.size() == required) break;
                        }
                    }
                    if (consumed.size() == required) break;
                }
            }
    
            actual = consumed.size();
            System.out.printf("- [%s] Gereken: %d, Adayın: %d --> %s\n", category, required, actual, (actual >= required ? "✔️" : "❌"));
    
            if (!category.equalsIgnoreCase("Başlıca Yazar") && actual >= required) {
                availableArticles.removeAll(consumed); // sadece kategori makalelerini tüket
            }
    
            if (actual < required) {
                allCriteriaMet = false;
            }
        }
    
        return allCriteriaMet;
    }    
    public ApplicationValidationResponse validateAndReturnDetails(Long applicationId) {
        boolean result = isApplicationValid(applicationId);
    
        Application application = applicationRepository.findWithArticlesAndAuthorsByApplicationId(applicationId);
        if (application == null) {
            throw new RuntimeException("Başvuru bulunamadı: ID = " + applicationId);
        }
    
        String position = application.getJobOffer().getPosition().getPositionName();
        String facultyGroup = application.getJobOffer().getDepartment().getFaculty().getFacultyGroup().getFacultyGroupName();
    
        return new ApplicationValidationResponse(position, facultyGroup, result);
    }    
}