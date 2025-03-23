package com.yazlab.academichub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yazlab.academichub.config.JwtProvider;
import com.yazlab.academichub.entities.JobOffer;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.request.CreateJobOfferRequest;
import com.yazlab.academichub.service.AdminService;
import com.yazlab.academichub.service.JobOfferService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/joboffer")
public class JobOfferController {

    private final JobOfferService jobOfferService;

    private final JwtProvider jwtProvider;

    private final AdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<?> createJobOffer(@RequestBody CreateJobOfferRequest request,
            @RequestHeader("Authorization") String jwt) throws AdminException {

        List<String> roles = jwtProvider.getUserRolesFromJwtToken(jwt);

        if (roles.contains("ADMIN")) {

            User user = adminService.getAdminByEmail(jwtProvider.getEmailFromJwtToken(jwt));

            JobOffer jobOffer = jobOfferService.createJobOffer(request, user);

            return new ResponseEntity<>(jobOffer, HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
