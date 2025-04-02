package com.yazlab.academichub.service;


import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.candidateDocuments.CandidateAward;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.AwardException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.CandidateAwardRepository;
import com.yazlab.academichub.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateAwardService {

    private final ApplicationRepository applicationRepository;

    private final CandidateAwardRepository candidateAwardRepository;

    @Transactional
    public ApiResponse addAward(Long applictionId, CandidateAward request) throws AwardException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new AwardException("Application could not find by Id -> " + applictionId.toString());
        }



        CandidateAward candidateAward = new CandidateAward();

        candidateAward.setCategory(request.getCategory());

        candidateAward.setUniName(request.getUniName());



        candidateAward.setYear(request.getYear());


        candidateAward.setPhotoName(request.getPhotoName());

        candidateAward.setApplication(application);

        candidateAwardRepository.save(candidateAward);

        application.addAward(candidateAward);

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Award has been loaded successfully!");

        return apiResponse;
    }

    public ApiResponse deleteAward(Long applictionId, Long awardId) throws ApplicationException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ApplicationException("Application could not find by Id -> " + applictionId.toString());
        }

        CandidateAward candidateAward = candidateAwardRepository.findByAwardIdAndApplicationId(awardId, applictionId);

        if (candidateAward == null) {
            throw new ApplicationException("Award could not find by Id -> " + awardId.toString());
        }

        candidateAwardRepository.delete(candidateAward);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Award delete successfully!");

        return apiResponse;

    }
}
