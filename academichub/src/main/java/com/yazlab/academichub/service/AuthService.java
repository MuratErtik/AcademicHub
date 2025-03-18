package com.yazlab.academichub.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yazlab.academichub.config.JwtProvider;
import com.yazlab.academichub.domain.USER_ROLE;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.exception.AuthException;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.request.SignupRequest;
import com.yazlab.academichub.response.AuthResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    public String createCandidate(SignupRequest req) {

        User user = userRepository.findByEmail(req.getEmail());

        if (user == null) {

            User newUser = new User();

            newUser.setEmail(req.getEmail());

            newUser.setLastname(req.getLastname());

            newUser.setMobileNo(req.getMobileNo());

            newUser.setName(req.getName());

            newUser.setTcNo(passwordEncoder.encode(req.getTcNo()));

            newUser.setUserRole(USER_ROLE.CANDIDATE);

            user = userRepository.save(newUser);

            // yeni nesneler olusturulabilir buradan -> basvuru vs...

        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(USER_ROLE.CANDIDATE.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);

    }

    public AuthResponse signIn(SignupRequest req) throws AuthException {

        User user = userRepository.findByEmail(req.getEmail());

        if (user == null) {
            createCandidate(req);
        }

        if (!passwordEncoder.matches(req.getTcNo(), user.getTcNo())) {
            throw new AuthException("Invalid Tc No!");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);

        String token = jwtProvider.generateToken(authentication);

        String message = "login successfully!";

        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(token);

        authResponse.setMessage(message);

        authResponse.setRole(USER_ROLE.valueOf(roleName));

        return authResponse;

    }
}
