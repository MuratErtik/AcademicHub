package com.yazlab.academichub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.candidateDocuments.CandidatePatent;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.PatentException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.CandidatePatentRepository;
import com.yazlab.academichub.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidatePatentService {

    private final ApplicationRepository applicationRepository;

    private final CandidatePatentRepository candidatePatentRepository;

    @Transactional
    public ApiResponse addPatent(Long applictionId, CandidatePatent request) throws PatentException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new PatentException("Application could not find by Id -> " + applictionId.toString());
        }

        List<CandidatePatent> existcandidatePatents = candidatePatentRepository.findByPatentName(request.getPatentName());

        boolean patentExist = existcandidatePatents.stream()
                .anyMatch(citation -> citation.getApplication() != null
                        && citation.getApplication().getApplicationId().equals(applictionId));

        if (patentExist) {

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("The Patent loaded already!");
            return apiResponse;
        }

        CandidatePatent candidatePatent = new CandidatePatent();

        candidatePatent.setCategory(request.getCategory());

        candidatePatent.setPatentName(request.getPatentName());

       candidatePatent.setYear(request.getYear());

        candidatePatent.setPhotoPath(request.getPhotoPath());

        candidatePatent.setApplication(application);

        candidatePatentRepository.save(candidatePatent);

        application.addPatent(candidatePatent);

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Patent has been loaded successfully!");

        return apiResponse;
    }

    public ApiResponse deletePatent(Long applictionId, Long patentId ) throws ApplicationException{

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ApplicationException("Application could not find by Id -> " + applictionId.toString());
        }

        CandidatePatent candidatePatent = candidatePatentRepository.findByPatentNameAndApplicationId(patentId, applictionId);

        if (candidatePatent==null) {
            throw new ApplicationException("Patent could not find by Id -> " + patentId.toString());
        }

        candidatePatentRepository.delete(candidatePatent);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Patent delete successfully!");

        return apiResponse;
        
    }
}
