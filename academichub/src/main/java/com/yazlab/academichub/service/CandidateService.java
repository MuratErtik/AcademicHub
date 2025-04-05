package com.yazlab.academichub.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.ApplicationStatus;
import com.yazlab.academichub.entities.JobOffer;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.exception.JobOfferException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.ApplicationStatusRepository;
import com.yazlab.academichub.repository.JobOfferRepository;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.response.ApiResponse;
import com.yazlab.academichub.response.JobOfferResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final JobOfferService jobOfferService;

    private final UserRepository userRepository;

    private final JobOfferRepository jobOfferRepository;

    private final ApplicationRepository applicationRepository;

    private final ApplicationStatusRepository applicationStatusRepository;

    public List<JobOfferResponse> listAllOffers() {

        LocalDateTime now = LocalDateTime.now();

        List<JobOffer> allJobOffers = jobOfferService.getAllJobOffer();

        List<JobOffer> jobOffersToList = allJobOffers.stream()
                .filter(offer -> offer.getTitle() != null)
                .filter(offer -> offer.getDescription() != null)
                .filter(offer -> offer.getDepartment() != null)
                .filter(offer -> offer.getPosition() != null)
                .filter(offer -> offer.getStartDate() != null && offer.getEndDate() != null)
                .filter(offer -> now.isAfter(offer.getStartDate()) && now.isBefore(offer.getEndDate()))
                .filter(offer -> !offer.getMinMaxPointCriterias().isEmpty()
                        && !offer.getPublicationCriterias().isEmpty())
                .collect(Collectors.toList());

        List<JobOfferResponse> jobOfferResponses = jobOffersToList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        return jobOfferResponses;
    }

    private JobOfferResponse convertToResponse(JobOffer offer) {

        JobOfferResponse response = new JobOfferResponse();

        response.setJobOfferId(offer.getJobOfferId());

        response.setTitle(offer.getTitle());

        response.setDescription(offer.getDescription());

        response.setDepartmentName(offer.getDepartment().getDepartmentName());

        response.setPositionName(offer.getPosition().getPositionName());

        response.setStartDate(offer.getStartDate());

        response.setEndDate(offer.getEndDate());

        response.setMinMaxPointCriterias(offer.getMinMaxPointCriterias());

        response.setPublicationCriterias(offer.getPublicationCriterias());

        return response;
    }

    @Transactional
    public ApiResponse createApplication(Long jobOfferId, Long userId) throws AdminException, JobOfferException {

        Application existApplication = applicationRepository.findByUserAndJobOffer(userId, jobOfferId);

        if (existApplication != null) {

            ApiResponse apiResponse = new ApiResponse();

            apiResponse.setMessage("The Application created already!");

            return apiResponse;
        }

        Application application = new Application();

        User candidate = userRepository.findByUserId(userId);

        if (candidate == null) {
            throw new AdminException("Candidate could not find by Id-> " + userId.toString());
        }

        application.setCandidate(candidate);

        JobOffer jobOffer = jobOfferRepository.findByJobOfferId(jobOfferId);

        if (jobOffer == null) {
            throw new JobOfferException("Job offer could not find by Id-> " + jobOfferId.toString());
        }

        application.setJobOffer(jobOffer);

        application.setApplicationDate(LocalDateTime.now());

        applicationRepository.save(application);

        jobOffer.addApplication(application);

        jobOfferRepository.save(jobOffer);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("The Application just created!");

        return apiResponse;
    }

    public ApiResponse completeApplication(Long applicationId) {
        Application existApplication = applicationRepository.findByApplicationId(applicationId);

        if (existApplication == null) {

            ApiResponse apiResponse = new ApiResponse();

            apiResponse.setMessage("The Application did not find!");

            return apiResponse;
        }

        ApplicationStatus applicationStatus = applicationStatusRepository.findByApplicationStatus("pending");

        

        if (applicationStatus == null) {

            ApiResponse apiResponse = new ApiResponse();

            apiResponse.setMessage("Status could not indicate!");

            return apiResponse;

        }

        // System.out.println(applicationStatus.getApplicationStatus());
        // System.out.println(applicationStatus.getApplicationStatusId());

        existApplication.setApplicationStatus(applicationStatus);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("The Application has been completed!");

        applicationRepository.save(existApplication);

        return apiResponse;

    }
}
