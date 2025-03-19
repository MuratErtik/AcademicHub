package com.yazlab.academichub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yazlab.academichub.domain.USER_ROLE;
import com.yazlab.academichub.exception.AuthException;
import com.yazlab.academichub.request.OtherSignupRequest;
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
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody SignupRequest signupRequest) throws AuthException {

        AuthResponse authResponse = authService.signIn(signupRequest);

        return ResponseEntity.ok(authResponse);

    }

    // @PostMapping("signup")
    // public ResponseEntity<AuthResponse> createCandidateHandler(@RequestBody
    // SignupRequest request){

    // String jwt = authService.createCandidate(request);

    // AuthResponse authResponse = new AuthResponse();

    // authResponse.setJwt(jwt);

    // authResponse.setMessage("Registered Successfully");

    // authResponse.setRole(USER_ROLE.CANDIDATE);

    // return ResponseEntity.ok(authResponse);
    // }

    @PostMapping("admin-signup")
    public ResponseEntity<AuthResponse> createAdminHandler(@RequestBody OtherSignupRequest request) {

        String jwt = authService.createAdmin(request);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);

        authResponse.setMessage("Registered Successfully");

        authResponse.setRole(USER_ROLE.ADMIN);

        return ResponseEntity.ok(authResponse);


    }

    @PostMapping("/admin-signin")
    public ResponseEntity<AuthResponse> adminLoginHandler(@RequestBody OtherSignupRequest signupRequest) throws AuthException {

        AuthResponse authResponse = authService.adminSignIn(signupRequest);

        return ResponseEntity.ok(authResponse);

    }





    @PostMapping("departmentmenager-signup")
    public ResponseEntity<AuthResponse> createdeparmentMenagerHandler(@RequestBody OtherSignupRequest request) {

        String jwt = authService.createDepartmentManager(request);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);

        authResponse.setMessage("Registered Successfully");

        authResponse.setRole(USER_ROLE.DEPARTMENT_MANAGER);

        return ResponseEntity.ok(authResponse);


    }

    @PostMapping("/departmentmenager-signin")
    public ResponseEntity<AuthResponse> departmentManagerLoginHandler(@RequestBody OtherSignupRequest signupRequest) throws AuthException {

        AuthResponse authResponse = authService.departmentManagerSignIn(signupRequest);

        return ResponseEntity.ok(authResponse);

    }

}
