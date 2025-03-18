package com.yazlab.academichub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yazlab.academichub.domain.USER_ROLE;
import com.yazlab.academichub.exception.AuthException;
import com.yazlab.academichub.request.SignupRequest;
import com.yazlab.academichub.response.AuthResponse;
import com.yazlab.academichub.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody SignupRequest signupRequest) throws AuthException{

        AuthResponse authResponse = authService.signIn(signupRequest);

        return ResponseEntity.ok(authResponse);

    }

    @PostMapping("signup")
    public ResponseEntity<AuthResponse> createCandidateHandler(@RequestBody SignupRequest request){

        String jwt = authService.createCandidate(request);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);

        authResponse.setMessage("Registered Successfully");

        authResponse.setRole(USER_ROLE.CANDIDATE);

        return ResponseEntity.ok(authResponse);
    }

}
    