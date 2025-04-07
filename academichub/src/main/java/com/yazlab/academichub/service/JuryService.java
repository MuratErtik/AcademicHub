package com.yazlab.academichub.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.JuryApplication;
import com.yazlab.academichub.repository.JuryApplicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JuryService {

    private final JuryApplicationRepository juryApplicationRepository;

    public Set<JuryApplication> getAllApplicationToFill(Long userId) {

        Set<JuryApplication> applications = juryApplicationRepository.findByUser(userId);

        return applications;

    }

    // doldursun yaniii
}
