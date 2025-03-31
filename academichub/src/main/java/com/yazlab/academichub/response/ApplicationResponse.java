package com.yazlab.academichub.response;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.yazlab.academichub.entities.candidateDocuments.CandidateArticle;

import lombok.Data;

@Data
public class ApplicationResponse {

    private Long applicationId;

    private Long userId;

    private LocalDateTime applicationDate;

    private String applicationStatusName;

    private Set<CandidateArticle> articles = new HashSet<>();
}
