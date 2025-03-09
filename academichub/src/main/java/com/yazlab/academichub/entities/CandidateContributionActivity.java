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
public class CandidateContributionActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long candidateContributionActivityId;

    private String category;

    private String departmentName;

    private String year ;

    private String photoLink;
}
