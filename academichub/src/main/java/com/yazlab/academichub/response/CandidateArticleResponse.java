package com.yazlab.academichub.response;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class CandidateArticleResponse {

    private Long candidateArticleId;

    private String articleName;

    private String articleTypeName;

    private String articleCategory;

    private int authorCount;

    private Set<CandidateAuthorResponse> authors = new HashSet<>();

    private String photoLink;

}
