package com.yazlab.academichub.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.JuryApplication;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.entities.candidateDocuments.CandidateArticle;
import com.yazlab.academichub.entities.candidateDocuments.CandidateAuthor;

import com.yazlab.academichub.repository.JuryApplicationRepository;
import com.yazlab.academichub.response.ApiResponse;
import com.yazlab.academichub.response.ApplicationResponseToJury;
import com.yazlab.academichub.response.CandidateArticleResponse;
import com.yazlab.academichub.response.CandidateAuthorResponse;
import com.yazlab.academichub.response.JuryApplicationResponse;
import com.yazlab.academichub.response.UserResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JuryService {

    private final JuryApplicationRepository juryApplicationRepository;

    public Set<ApplicationResponseToJury> getAllApplicationToFill(Long userId) {

        Set<JuryApplication> applications = juryApplicationRepository.findByUser(userId);

        Set<ApplicationResponseToJury> apps = convert(applications);

        return apps;

    }

    public Set<ApplicationResponseToJury> convert(Set<JuryApplication> applications) {
        return applications.stream()
                .map(this::convertSingleApplication)
                .collect(Collectors.toSet());
    }

    private ApplicationResponseToJury convertSingleApplication(JuryApplication application) {
        ApplicationResponseToJury applicationResponse = new ApplicationResponseToJury();

        applicationResponse.setId(application.getId());

        applicationResponse.setApplicationId(application.getApplication().getApplicationId());

        if (application.getApplication().getCandidate() != null) {
            applicationResponse.setUserId(application.getApplication().getCandidate().getUserId());
        }

        applicationResponse.setApplicationDate(application.getApplication().getApplicationDate());

        if (application.getApplication().getArticles() != null) {
            Set<CandidateArticleResponse> candidateArticleResponses = convertArticle(
                    application.getApplication().getArticles());
            applicationResponse.setArticles(candidateArticleResponses);
        } else {
            applicationResponse.setArticles(new HashSet<>());
        }

        if (application.getApplication().getSmas() != null) {
            applicationResponse.setSmas(application.getApplication().getSmas());
        } else {
            applicationResponse.setSmas(new HashSet<>());
        }

        if (application.getApplication().getBooks() != null) {
            applicationResponse.setBooks(application.getApplication().getBooks());
        } else {
            applicationResponse.setBooks(new HashSet<>());
        }

        if (application.getApplication().getCitations() != null) {
            applicationResponse.setCitations(application.getApplication().getCitations());
        } else {
            applicationResponse.setCitations(new HashSet<>());
        }

        if (application.getApplication().getEducationActions() != null) {
            applicationResponse.setEducationActions(application.getApplication().getEducationActions());
        } else {
            applicationResponse.setEducationActions(new HashSet<>());
        }

        if (application.getApplication().getThesisSupervisions() != null) {
            applicationResponse.setThesisSupervisions(application.getApplication().getThesisSupervisions());
        } else {
            applicationResponse.setThesisSupervisions(new HashSet<>());
        }

        if (application.getApplication().getPatents() != null) {
            applicationResponse.setPatents(application.getApplication().getPatents());
        } else {
            applicationResponse.setPatents(new HashSet<>());
        }

        if (application.getApplication().getResearchProjects() != null) {
            applicationResponse.setResearchProjects(application.getApplication().getResearchProjects());
        } else {
            applicationResponse.setResearchProjects(new HashSet<>());
        }

        if (application.getApplication().getEditorships() != null) {
            applicationResponse.setEditorships(application.getApplication().getEditorships());
        } else {
            applicationResponse.setEditorships(new HashSet<>());
        }

        if (application.getApplication().getAwards() != null) {
            applicationResponse.setAwards(application.getApplication().getAwards());
        } else {
            applicationResponse.setAwards(new HashSet<>());
        }

        if (application.getApplication().getCandidateContributionActivities() != null) {
            applicationResponse
                    .setContributionActivities(application.getApplication().getCandidateContributionActivities());
        } else {
            applicationResponse.setContributionActivities(new HashSet<>());
        }

        applicationResponse.setResponse(application.getJuryevalutationResponse());

        applicationResponse.setApproved(application.isApproved());

        return applicationResponse;

    }

    public Set<CandidateArticleResponse> convertArticle(Set<CandidateArticle> articles) {
        return articles.stream()
                .map(this::convertSingleArticleResponse)
                .collect(Collectors.toSet());
    }

    public CandidateArticleResponse convertSingleArticleResponse(CandidateArticle candidateArticle) {

        CandidateArticleResponse candidateArticleResponse = new CandidateArticleResponse();

        candidateArticleResponse.setCandidateArticleId(candidateArticle.getCandidateArticleId());

        candidateArticleResponse.setArticleName(candidateArticle.getArticleName());

        candidateArticleResponse.setArticleCategory(candidateArticle.getArticleCategory());

        candidateArticleResponse.setArticleTypeName(candidateArticle.getArticleType().getArticleTypeName());

        candidateArticleResponse.setAuthorCount(candidateArticle.getAuthorCount());

        Set<CandidateAuthorResponse> authorResponses = convertAuthor(candidateArticle.getAuthors());

        candidateArticleResponse.setAuthors(authorResponses);

        candidateArticleResponse.setPhotoLink(candidateArticle.getPhotoLink());

        return candidateArticleResponse;

    }

    public Set<JuryApplicationResponse> convertJuryApp(Set<JuryApplication> juries) {
        return juries.stream()
                .map(this::convertSingleEvalaution)
                .collect(Collectors.toSet());
    }

    public JuryApplicationResponse convertSingleEvalaution(JuryApplication juryApplication) {

        JuryApplicationResponse juryApplicationResponse = new JuryApplicationResponse();

        juryApplicationResponse.setApplicationId(juryApplication.getApplication().getApplicationId());

        juryApplicationResponse.setApproved(juryApplication.isApproved());

        juryApplicationResponse.setId(juryApplication.getId());

        UserResponse jury = convertJuryToResponse(juryApplication.getJury());

        jury.setUserRole("JURI");

        jury.setUserId(juryApplication.getJury().getUserId());

        juryApplicationResponse.setJury(jury);

        juryApplicationResponse.setJuryevalutationResponse(juryApplication.getJuryevalutationResponse());

        return juryApplicationResponse;

    }

    public Set<UserResponse> convertJuries(Set<User> juries) {
        return juries.stream()
                .map(this::convertJuryToResponse)
                .collect(Collectors.toSet());
    }

    public UserResponse convertJuryToResponse(User jury) {

        UserResponse userResponse = new UserResponse();

        userResponse.setDepartmentName(jury.getDepartment().getDepartmentName());

        userResponse.setEmail(jury.getEmail());

        userResponse.setLastname(jury.getLastname());

        userResponse.setMobileNo(jury.getMobileNo());

        userResponse.setName(jury.getName());

        // userResponse.setUserId(jury.getUserId());

        // userResponse.setUserRole(jury.getUserRole().getUserRole());

        return userResponse;
    }

    public Set<CandidateAuthorResponse> convertAuthor(Set<CandidateAuthor> authors) {
        return authors.stream()
                .map(this::convertSingleAuthorResponse)
                .collect(Collectors.toSet());
    }

    public CandidateAuthorResponse convertSingleAuthorResponse(CandidateAuthor candidateAuthor) {

        CandidateAuthorResponse candidateAuthorResponse = new CandidateAuthorResponse();

        candidateAuthorResponse.setCandidateArticleId(candidateAuthor.getCandidateArticleId());

        candidateAuthorResponse.setName(candidateAuthor.getName());

        candidateAuthorResponse.setSurname(candidateAuthor.getSurname());

        candidateAuthorResponse.setAuthorTypeName(candidateAuthor.getAuthorType().getAuthorTypeName());

        return candidateAuthorResponse;

    }

    // complete the evaluation!

    @Transactional
    public ApiResponse completeEvaluationOfApplication(Long applicationId, String response, Long userId,
            Boolean isApproved) {

        JuryApplication juryApplication = juryApplicationRepository.findByJuryIdAndApplicationId(userId, applicationId);

        if (juryApplication == null) {
            throw new RuntimeException("Jury Application not found!"); 
        }

        juryApplication.setJuryevalutationResponse(response);
        juryApplication.setApproved(isApproved);

        juryApplicationRepository.save(juryApplication);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("The Evaluation completed successfully!");

        return apiResponse;
    }

}
