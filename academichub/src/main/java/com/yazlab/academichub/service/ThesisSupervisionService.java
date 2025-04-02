package com.yazlab.academichub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.candidateDocuments.CandidateThesisSupervision;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.ThesisSupervisionException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.CandidateThesisSupervisionRepository;
import com.yazlab.academichub.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThesisSupervisionService {

    private final ApplicationRepository applicationRepository;

    private final CandidateThesisSupervisionRepository candidateThesisSupervisionRepository;

    @Transactional
    public ApiResponse addThesisSupervision(Long applictionId, CandidateThesisSupervision request) throws ThesisSupervisionException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ThesisSupervisionException("Application could not find by Id -> " + applictionId.toString());
        }

        List<CandidateThesisSupervision> existcandidateThesisSupervisions = candidateThesisSupervisionRepository.findByThesisName(request.getThesisName());

        boolean educationExist = existcandidateThesisSupervisions.stream()
                .anyMatch(act -> act.getApplication() != null
                        && act.getApplication().getApplicationId().equals(applictionId));

        if (educationExist) {

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("The Thesis Supervision loaded already!");
            return apiResponse;
        }

        CandidateThesisSupervision candidateThesisSupervision = new CandidateThesisSupervision();

        candidateThesisSupervision.setCategory(request.getCategory());

        candidateThesisSupervision.setStudentName(request.getStudentName());

        candidateThesisSupervision.setThesisName(request.getThesisName());

        candidateThesisSupervision.setEnstitu(request.getEnstitu());

        candidateThesisSupervision.setYear(request.getYear());

        candidateThesisSupervision.setPhotoLink(request.getPhotoLink());

        candidateThesisSupervision.setApplication(application);

        candidateThesisSupervisionRepository.save(candidateThesisSupervision);

        application.addThesisSupervisions(candidateThesisSupervision);

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Thesis Supervision has been loaded successfully!");

        return apiResponse;
    }

    public ApiResponse deleteThesisSupervision(Long applictionId, Long thesisEduationId ) throws ApplicationException{

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ApplicationException("Application could not find by Id -> " + applictionId.toString());
        }

        CandidateThesisSupervision candidateThesisSupervision = candidateThesisSupervisionRepository.findByThesisNameAndApplicationId(thesisEduationId, applictionId);

        if (candidateThesisSupervision==null) {
            throw new ApplicationException("Thesis Supervision could not find by Id -> " + thesisEduationId.toString());
        }

        candidateThesisSupervisionRepository.delete(candidateThesisSupervision);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Thesis Supervision has been deleted successfully!");

        return apiResponse;
        
    }
}
