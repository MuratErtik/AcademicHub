package com.yazlab.academichub.entities;



import com.yazlab.academichub.domain.USER_ROLE;

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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "tc_no", unique = true, nullable = false)
    private String tcNo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private USER_ROLE userRole;

    private String name;

    private String lastname;

    private String email;

    private String password;

    private Long mobileNo;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    


}
