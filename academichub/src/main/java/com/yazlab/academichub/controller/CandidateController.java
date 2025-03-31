package com.yazlab.academichub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yazlab.academichub.config.JwtProvider;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.exception.JobOfferException;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.response.ApiResponse;
import com.yazlab.academichub.response.JobOfferResponse;
import com.yazlab.academichub.service.CandidateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    @GetMapping("/getAllJobOffer")
    public ResponseEntity<List<JobOfferResponse>> getAJobOffers(@RequestHeader("Authorization") String jwt)
            throws AdminException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        String role = jwtProvider.getRolefromjwtByEmail(email);

        if (role.equals("ADAY")) {
            List<JobOfferResponse> jobOffers = candidateService.listAllOffers();

            return ResponseEntity.ok(jobOffers);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{jobOfferId}/start-application")
    public ResponseEntity<ApiResponse> createApplication(@RequestHeader("Authorization") String jwt,
            @PathVariable Long jobOfferId) throws AdminException, JobOfferException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);

        User user = userRepository.findByEmail(email);

        Long userId = (Long)user.getUserId();

        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateService.createApplication(jobOfferId, userId);

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
