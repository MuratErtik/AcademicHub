package com.yazlab.academichub.controller;

import com.yazlab.academichub.exception.AuthException;
import com.yazlab.academichub.request.LoginRequest;
import com.yazlab.academichub.request.OtherSignupRequest;
import com.yazlab.academichub.request.SignupRequest;
import com.yazlab.academichub.response.AuthResponse;
import com.yazlab.academichub.response.LoginResponse;
import com.yazlab.academichub.security.JwtTokenProvider;
import com.yazlab.academichub.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    // Eski login işlemi
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody SignupRequest signupRequest) throws AuthException {
        AuthResponse authResponse = authService.signIn(signupRequest);
        return ResponseEntity.ok(authResponse);
    }

    // Yeni JWT login endpoint'i
    @PostMapping("/login-jwt")
    public ResponseEntity<LoginResponse> loginJwt(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    // Kullanıcı kayıt
    @PostMapping("/user-signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody OtherSignupRequest request) {
        String jwt = authService.createUser(request, request.getRole());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully");
        authResponse.setRole(request.getRole());

        return ResponseEntity.ok(authResponse);
    }

    // Kullanıcı login (diğer yol)
    @PostMapping("/user-signin")
    public ResponseEntity<AuthResponse> userLoginHandler(@RequestBody OtherSignupRequest request) throws AuthException {
        AuthResponse authResponse = authService.userSignIn(request);
        return ResponseEntity.ok(authResponse);
    }
}