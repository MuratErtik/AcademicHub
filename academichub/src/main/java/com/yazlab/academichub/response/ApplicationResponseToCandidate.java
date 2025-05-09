package com.yazlab.academichub.response;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.yazlab.academichub.entities.candidateDocuments.CandidateAward;
import com.yazlab.academichub.entities.candidateDocuments.CandidateBook;
import com.yazlab.academichub.entities.candidateDocuments.CandidateCitation;
import com.yazlab.academichub.entities.candidateDocuments.CandidateContributionActivity;
import com.yazlab.academichub.entities.candidateDocuments.CandidateEditorship;
import com.yazlab.academichub.entities.candidateDocuments.CandidateEducationAction;
import com.yazlab.academichub.entities.candidateDocuments.CandidatePatent;
import com.yazlab.academichub.entities.candidateDocuments.CandidateResearchProject;
import com.yazlab.academichub.entities.candidateDocuments.CandidateSMA;
import com.yazlab.academichub.entities.candidateDocuments.CandidateThesisSupervision;

import lombok.Data;

@Data
public class ApplicationResponseToCandidate {

    private Long applicationId;

    private Long userId;

    private LocalDateTime applicationDate;

    private String applicationStatusName;

    private Set<CandidateArticleResponse> articles = new HashSet<>();

    private Set<CandidateSMA> smas = new HashSet<>();

    private Set<CandidateBook> books = new HashSet<>();

    private Set<CandidateCitation> citations = new HashSet<>();

    private Set<CandidateEducationAction> educationActions = new HashSet<>();

    private Set<CandidateThesisSupervision> thesisSupervisions = new HashSet<>();

    private Set<CandidatePatent> patents = new HashSet<>();

    private Set<CandidateResearchProject> researchProjects = new HashSet<>();

    private Set<CandidateEditorship> editorships = new HashSet<>();

    private Set<CandidateAward> awards = new HashSet<>();

    private Set<CandidateContributionActivity> contributionActivities = new HashSet<>();
}
