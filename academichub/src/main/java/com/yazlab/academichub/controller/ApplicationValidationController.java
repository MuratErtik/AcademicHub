package com.yazlab.academichub.controller;

import com.yazlab.academichub.response.ApplicationValidationResponse;
import com.yazlab.academichub.service.ApplicationValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationValidationController {

    private final ApplicationValidationService applicationValidationService;

    // --- Aşağıdaki metodlar şimdilik devre dışı bırakıldı ---
    /*
    @GetMapping("/print-articles/{applicationId}")
    public void printApplicationArticles(@PathVariable Long applicationId) {
        applicationValidationService.printApplicationArticles(applicationId);
    }

    @GetMapping("/article-category-count/{applicationId}")
    public Map<String, Integer> countArticlesByCategory(@PathVariable Long applicationId) {
        return applicationValidationService.countArticlesByCategory(applicationId);
    }

    @GetMapping("/print-publication-criteria/{applicationId}")
    public void printPublicationCriteria(@PathVariable Long applicationId) {
        applicationValidationService.printPublicationCriteria(applicationId);
    }
    */

    // ✅ GEÇERLİLİK KONTROLÜ: Başvuru kriterleri karşılıyor mu?
    @GetMapping("/validate/{applicationId}")
    public boolean isApplicationValid(@PathVariable Long applicationId) {
        return applicationValidationService.isApplicationValid(applicationId);
    }

    @GetMapping("/validate-detailed/{applicationId}")
    public ApplicationValidationResponse validateApplicationDetailed(@PathVariable Long applicationId) {
        return applicationValidationService.validateAndReturnDetails(applicationId);
    }
}