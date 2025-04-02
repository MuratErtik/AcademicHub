package com.yazlab.academichub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.candidateDocuments.CandidateEducationAction;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.EducationActionException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.CandidateEducationActionRepository;
import com.yazlab.academichub.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationActionService {

    private final ApplicationRepository applicationRepository;

    private final CandidateEducationActionRepository candidateEducationActionRepository;

    @Transactional
    public ApiResponse addEducationAction(Long applictionId, CandidateEducationAction request) throws EducationActionException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new EducationActionException("Application could not find by Id -> " + applictionId.toString());
        }

        List<CandidateEducationAction> existCandidateEducationActions = candidateEducationActionRepository.findByLectureName(request.getLectureName());

        boolean educationExist = existCandidateEducationActions.stream()
                .anyMatch(act -> act.getApplication() != null
                        && act.getApplication().getApplicationId().equals(applictionId));

        if (educationExist) {

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("The Education Action loaded already!");
            return apiResponse;
        }

        CandidateEducationAction candidateEducationAction = new CandidateEducationAction();

        candidateEducationAction.setEducationActionCategory(request.getEducationActionCategory());

        candidateEducationAction.setLectureName(request.getLectureName());

        candidateEducationAction.setLectureLanguage(request.isLectureLanguage());

        candidateEducationAction.setProgramName(request.getProgramName());

        candidateEducationAction.setTerm(request.getTerm());

        candidateEducationAction.setYear(request.getYear());

        candidateEducationAction.setPhotoLink(request.getPhotoLink());

        candidateEducationAction.setApplication(application);

        candidateEducationActionRepository.save(candidateEducationAction);

        application.addEducationAction(candidateEducationAction);

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Education Action has been loaded successfully!");

        return apiResponse;
    }

    public ApiResponse deleteEducationAction(Long applictionId, Long educationActionId ) throws ApplicationException{

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ApplicationException("Application could not find by Id -> " + applictionId.toString());
        }

        CandidateEducationAction candidateEducationAction = candidateEducationActionRepository.findByLectureNameAndApplicationId(educationActionId, applictionId);

        if (candidateEducationAction==null) {
            throw new ApplicationException("Education Action could not find by Id -> " + educationActionId.toString());
        }

        candidateEducationActionRepository.delete(candidateEducationAction);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Eduation Action has been deleted successfully!");

        return apiResponse;
        
    }
}
