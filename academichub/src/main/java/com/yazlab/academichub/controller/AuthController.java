package com.yazlab.academichub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yazlab.academichub.entities.UserRole;
import com.yazlab.academichub.exception.AuthException;
import com.yazlab.academichub.repository.UserRoleRepository;
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

    private final UserRoleRepository userRoleRepository;

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

    @PostMapping("user-signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody OtherSignupRequest request) {

        UserRole userRole = userRoleRepository.findUserRoleByUserRole(request.getRole());


        String jwt = authService.createUser(request, userRole);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);

        authResponse.setMessage("Registered Successfully");

        authResponse.setRole(userRole.getUserRole());


        return ResponseEntity.ok(authResponse);

    }

    @PostMapping("/user-signin")
    public ResponseEntity<AuthResponse> userLoginHandler(@RequestBody OtherSignupRequest request) throws AuthException {

        AuthResponse authResponse = authService.userSignIn(request);

        return ResponseEntity.ok(authResponse);

    }

}
