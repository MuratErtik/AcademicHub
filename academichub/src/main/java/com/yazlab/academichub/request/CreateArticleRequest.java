package com.yazlab.academichub.request;


import java.util.Set;

import lombok.Data;

@Data
public class CreateArticleRequest {

    private String articleName;

    private String articleTypeName;

    private String articleCategory; 

    private int authorCount;

    private Set<AddAuthorRequest> authors;

    private String photoLink;

}
