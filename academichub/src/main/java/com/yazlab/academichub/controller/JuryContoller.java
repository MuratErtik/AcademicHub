package com.yazlab.academichub.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yazlab.academichub.config.JwtProvider;
import com.yazlab.academichub.entities.JuryApplication;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.service.JuryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/jury")
public class JuryContoller {

    private final JwtProvider jwtProvider;

    private final JuryService juryService;

    private final UserRepository userRepository;

    @GetMapping("/get-all-application")
    public ResponseEntity<Set<JuryApplication>> getAllApplication(@RequestHeader("Authorization") String jwt) throws AdminException{

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        String role = jwtProvider.getRolefromjwtByEmail(email);

        User user = userRepository.findByEmail(email);

        if (role.equals("JURI")) {

            Set<JuryApplication> applications = juryService.getAllApplicationToFill(user.getUserId());

            return new ResponseEntity<>(applications, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    
    }

}
