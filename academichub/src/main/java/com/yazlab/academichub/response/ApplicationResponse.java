package com.yazlab.academichub.response;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.yazlab.academichub.entities.candidateDocuments.CandidateBook;
import com.yazlab.academichub.entities.candidateDocuments.CandidateSMA;

import lombok.Data;

@Data
public class ApplicationResponse {

    private Long applicationId;

    private Long userId;

    private LocalDateTime applicationDate;

    private String applicationStatusName;

    private Set<CandidateArticleResponse> articles = new HashSet<>();

    private Set<CandidateSMA> smas = new HashSet<>();

    private Set<CandidateBook> books = new HashSet<>();


}
