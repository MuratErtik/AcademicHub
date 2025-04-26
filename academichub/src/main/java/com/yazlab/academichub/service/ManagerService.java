package com.yazlab.academichub.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.ApplicationStatus;
import com.yazlab.academichub.entities.JuryApplication;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.AuthException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.ApplicationStatusRepository;
import com.yazlab.academichub.repository.JuryApplicationRepository;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.request.OtherSignupRequest;
import com.yazlab.academichub.response.ApiResponse;
import com.yazlab.academichub.response.DecisionResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ApplicationRepository applicationRepository;

    private final UserRepository userRepository;

    private final JuryApplicationRepository juryApplicationRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationStatusRepository applicationStatusRepository;

    public ApiResponse addJuryToApplication(Long applicationId, OtherSignupRequest request)
            throws ApplicationException, AuthException {

        Application applicationToAddJury = applicationRepository.findByApplicationId(applicationId);

        if (applicationToAddJury == null) {
            throw new ApplicationException("The Application could not find to adding jury in it.");
        }

        User jury = userRepository.findByEmail(request.getEmail());

        if (jury == null) {
            throw new ApplicationException("Jury could not find to adding in application.");
        }

        if (jury.getUserRole().getUserRole().equals("JURI")) {

            if (!passwordEncoder.matches(request.getTcNo(), jury.getTcNo())) {
                throw new AuthException("Invalid Tc No!");
            }

            JuryApplication existReplacement = juryApplicationRepository.findByJuryIdAndApplicationId(applicationId,
                    jury.getUserId());

            if (existReplacement != null) {
                throw new ApplicationException("Replacement completed already!");

            }

            JuryApplication juryApplication = new JuryApplication();

            juryApplication.setApplication(applicationToAddJury);

            juryApplication.setJury(jury);

            juryApplicationRepository.save(juryApplication);

            applicationToAddJury.addJuryEvaluation(juryApplication);

            ApplicationStatus applicationStatus = applicationStatusRepository.findByApplicationStatus("processing");

            applicationToAddJury.setApplicationStatus(applicationStatus);

            applicationRepository.save(applicationToAddJury);

            ApiResponse apiResponse = new ApiResponse();

            apiResponse.setMessage("Replacement has been completed successfully");

            return apiResponse;
        }

        else {
            ApiResponse apiResponse = new ApiResponse();

            apiResponse.setMessage("User have not Jury Role!");

            return apiResponse;
        }
    }

    public ApiResponse finishEvaluation(Long applicationId, DecisionResponse decision) {

        Application application = applicationRepository.findByApplicationId(applicationId);

        if (decision.getIsApproved()) {
            ApplicationStatus applicationStatus = applicationStatusRepository.findByApplicationStatus("approved");
            application.setApplicationStatus(applicationStatus);
        } else {
            ApplicationStatus applicationStatus = applicationStatusRepository.findByApplicationStatus("rejected");
            application.setApplicationStatus(applicationStatus);
        }

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Menager Decision has been processed successfully!");

        return apiResponse;
    }

}
