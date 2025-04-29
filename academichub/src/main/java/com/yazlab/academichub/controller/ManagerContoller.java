package com.yazlab.academichub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yazlab.academichub.config.JwtProvider;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.AuthException;
import com.yazlab.academichub.request.OtherSignupRequest;
import com.yazlab.academichub.response.ApiResponse;
import com.yazlab.academichub.response.DecisionResponse;
import com.yazlab.academichub.service.ManagerService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/manager")
public class ManagerContoller {

    private final JwtProvider jwtProvider;

    private final ManagerService managerService;

    @PostMapping("/add-jury-to-application/{applicationId}")
    public ResponseEntity<ApiResponse> addJuryToApplication(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody OtherSignupRequest request) throws ApplicationException, AdminException, AuthException, MessagingException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        String role = jwtProvider.getRolefromjwtByEmail(email);

        if (role.equals("YONETICI")) {

            ApiResponse apiResponse = managerService.addJuryToApplication(applicationId, request);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/finish-application/{applicationId}")
    public ResponseEntity<ApiResponse> finishApplication(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody DecisionResponse request) throws ApplicationException, AdminException, AuthException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        String role = jwtProvider.getRolefromjwtByEmail(email);

        if (role.equals("YONETICI")) {

            ApiResponse apiResponse = managerService.finishEvaluation(applicationId, request);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
