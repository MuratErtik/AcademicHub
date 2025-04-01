package com.yazlab.academichub.request;

import lombok.Data;

@Data
public class AddAuthorRequest {

    private String name;

    private String surname;

    private String authorTypeName;
}
