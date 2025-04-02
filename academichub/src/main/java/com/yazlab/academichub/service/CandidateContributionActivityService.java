package com.yazlab.academichub.service;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.candidateDocuments.CandidateContributionActivity;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.AwardException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.CandidateContributionActivityRepository;
import com.yazlab.academichub.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateContributionActivityService {

    private final ApplicationRepository applicationRepository;

    private final CandidateContributionActivityRepository candidateContributionActivityRepository;

    @Transactional
    public ApiResponse addca(Long applictionId, CandidateContributionActivity request) throws AwardException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new AwardException("Application could not find by Id -> " + applictionId.toString());
        }



        CandidateContributionActivity candidateContributionActivity = new CandidateContributionActivity();

        candidateContributionActivity.setCategory(request.getCategory());

        candidateContributionActivity.setDepartmentName(request.getDepartmentName());


        candidateContributionActivity.setYear(request.getYear());


        candidateContributionActivity.setPhotoLink(request.getPhotoLink());

        candidateContributionActivity.setApplication(application);

        candidateContributionActivityRepository.save(candidateContributionActivity);

        application.addCandidateContributionActivity(candidateContributionActivity);

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Award has been loaded successfully!");

        return apiResponse;
    }

    public ApiResponse deleteCa(Long applictionId, Long caId) throws ApplicationException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ApplicationException("Application could not find by Id -> " + applictionId.toString());
        }

        CandidateContributionActivity candidateContributionActivity = candidateContributionActivityRepository.findByIdAndApplicationId(caId,applictionId);

        if (candidateContributionActivity == null) {
            throw new ApplicationException("CandidateContributionActivity could not find by Id -> " + caId.toString());
        }

        candidateContributionActivityRepository.delete(candidateContributionActivity);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("CandidateContributionActivity delete successfully!");

        return apiResponse;

    }
}
