package com.yazlab.academichub.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SupportingDocument {
    //Article 7.1 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supportingDocumentId;

    private String documentName;

    private String description1;

    private String description2;

    private String description3;

    private String description4;

    private String description5;

}
