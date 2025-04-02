package com.yazlab.academichub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.candidateDocuments.CandidateCitation;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.CitationException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.CandidateCitationRepository;
import com.yazlab.academichub.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateCitationService {

    private final ApplicationRepository applicationRepository;

    private final CandidateCitationRepository candidateCitationRepository;

    @Transactional
    public ApiResponse addCitation(Long applictionId, CandidateCitation request) throws CitationException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new CitationException("Application could not find by Id -> " + applictionId.toString());
        }

        List<CandidateCitation> existCandidateCitations = candidateCitationRepository.findByArticleToCitation(request.getArticleToCitation());

        boolean citationExist = existCandidateCitations.stream()
                .anyMatch(citation -> citation.getApplication() != null
                        && citation.getApplication().getApplicationId().equals(applictionId));

        if (citationExist) {

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("The Citation loaded already!");
            return apiResponse;
        }

        CandidateCitation candidateCitation = new CandidateCitation();

        candidateCitation.setCitationCategory(request.getCitationCategory());

        candidateCitation.setArticleToCitation(request.getArticleToCitation());

        candidateCitation.setNumberOfCitation(request.getNumberOfCitation());

        candidateCitation.setPhotoLink(request.getPhotoLink());

        candidateCitation.setApplication(application);

        candidateCitationRepository.save(candidateCitation);

        application.addCitation(candidateCitation);

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("book has been loaded successfully!");

        return apiResponse;
    }

    public ApiResponse deleteCitation(Long applictionId, Long citationId ) throws ApplicationException{

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ApplicationException("Application could not find by Id -> " + applictionId.toString());
        }

        CandidateCitation candidateCitation = candidateCitationRepository.findByCitationNameAndApplicationId(citationId, applictionId);

        if (candidateCitation==null) {
            throw new ApplicationException("SMA could not find by Id -> " + citationId.toString());
        }

        candidateCitationRepository.delete(candidateCitation);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Citation delete successfully!");

        return apiResponse;
        
    }
}
