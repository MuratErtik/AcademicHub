package com.yazlab.academichub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.candidateDocuments.CandidateSMA;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.SmaException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.CandidateSMARepository;
import com.yazlab.academichub.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateSmaService {

    private final ApplicationRepository applicationRepository;

    private final CandidateSMARepository candidateSMARepository;

    @Transactional
    public ApiResponse addSma(Long applictionId, CandidateSMA request) throws SmaException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new SmaException("Application could not find by Id -> " + applictionId.toString());
        }

        List<CandidateSMA> existCandidateSmas = candidateSMARepository.findBySmaName(request.getSmaName());

        boolean smaExist = existCandidateSmas.stream()
                .anyMatch(sma -> sma.getApplication() != null
                        && sma.getApplication().getApplicationId().equals(applictionId));

        if (smaExist) {

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("The Scientific Meeting Activity loaded already!");
            return apiResponse;
        }

        CandidateSMA candidateSMA = new CandidateSMA();

        candidateSMA.setSMACategory(request.getSMACategory());

        candidateSMA.setSmaName(request.getSmaName());

        candidateSMA.setConferenceName(request.getConferenceName());

        candidateSMA.setPlace(request.getPlace());

        candidateSMA.setDate(request.getDate());

        candidateSMA.setPhotoLink(request.getPhotoLink());

        candidateSMA.setApplication(application);

        candidateSMARepository.save(candidateSMA);

        application.addSma(candidateSMA);

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Scientific Meeting Activity has been loaded successfully!");

        return apiResponse;
    }

    public ApiResponse deleteSma(Long applictionId, Long smaId ) throws ApplicationException{

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ApplicationException("Application could not find by Id -> " + applictionId.toString());
        }

        CandidateSMA candidateSMA = candidateSMARepository.findBySmaNameAndApplicationId(smaId, applictionId);

        if (candidateSMA==null) {
            throw new ApplicationException("SMA could not find by Id -> " + smaId.toString());
        }

        candidateSMARepository.delete(candidateSMA);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("SMA delete successfully!");

        return apiResponse;
        
    }
}
