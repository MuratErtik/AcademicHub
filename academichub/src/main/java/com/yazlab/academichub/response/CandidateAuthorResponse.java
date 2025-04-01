package com.yazlab.academichub.response;

import lombok.Data;

@Data
public class CandidateAuthorResponse {

    private Long candidateArticleId;

    private String name;

    private String surname;

    private String authorTypeName;
}
