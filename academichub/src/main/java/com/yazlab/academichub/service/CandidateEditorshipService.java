package com.yazlab.academichub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.candidateDocuments.CandidateEditorship;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.EducationActionException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.CandidateEditorshipRepository;
import com.yazlab.academichub.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateEditorshipService {

    private final ApplicationRepository applicationRepository;

    private final CandidateEditorshipRepository candidateEditorshipRepository;

    @Transactional
    public ApiResponse addEditorship(Long applictionId, CandidateEditorship request) throws EducationActionException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new EducationActionException("Application could not find by Id -> " + applictionId.toString());
        }

        List<CandidateEditorship> existcandidateEditorships = candidateEditorshipRepository.findByName(request.getName());

        boolean educationExist = existcandidateEditorships.stream()
                .anyMatch(act -> act.getApplication() != null
                        && act.getApplication().getApplicationId().equals(applictionId));

        if (educationExist) {

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("The Editorship Action loaded already!");
            return apiResponse;
        }

        CandidateEditorship candidateEditorship = new CandidateEditorship();

        candidateEditorship.setCategory(request.getCategory());

        candidateEditorship.setName(request.getName());


        candidateEditorship.setNumber(request.getNumber());


        candidateEditorship.setYear(request.getYear());

        candidateEditorship.setPhotoPath(request.getPhotoPath());

        candidateEditorship.setApplication(application);

        candidateEditorshipRepository.save(candidateEditorship);

        application.addEditorship(candidateEditorship);

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Editorship Action has been loaded successfully!");

        return apiResponse;
    }

    public ApiResponse deleteEditorship(Long applictionId, Long educationActionId ) throws ApplicationException{

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ApplicationException("Application could not find by Id -> " + applictionId.toString());
        }

        CandidateEditorship candidateEditorship = candidateEditorshipRepository.findByNameAndApplicationId(educationActionId, applictionId);

        if (candidateEditorship==null) {
            throw new ApplicationException("Editorship Action could not find by Id -> " + educationActionId.toString());
        }

        candidateEditorshipRepository.delete(candidateEditorship);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Editorship Action has been deleted successfully!");

        return apiResponse;
        
    }
}
