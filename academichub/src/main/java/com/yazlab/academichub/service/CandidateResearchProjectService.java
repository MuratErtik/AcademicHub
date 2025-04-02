package com.yazlab.academichub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.candidateDocuments.CandidateResearchProject;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.ResearchProjectException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.CandidateResearchProjectRepository;
import com.yazlab.academichub.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateResearchProjectService {

    private final ApplicationRepository applicationRepository;

    private final CandidateResearchProjectRepository candidateResearchProjectRepository;

    @Transactional
    public ApiResponse addResearchProject(Long applictionId, CandidateResearchProject request) throws ResearchProjectException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ResearchProjectException("Application could not find by Id -> " + applictionId.toString());
        }

        List<CandidateResearchProject> existcandidateResearchProjects = candidateResearchProjectRepository.findByName(request.getName());

        boolean ResearchProjectExist = existcandidateResearchProjects.stream()
                .anyMatch(p -> p.getApplication() != null
                        && p.getApplication().getApplicationId().equals(applictionId));

        if (ResearchProjectExist) {

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("The Research Project loaded already!");
            return apiResponse;
        }

        CandidateResearchProject candidateResearchProject = new CandidateResearchProject();

        candidateResearchProject.setCategory(request.getCategory());

        candidateResearchProject.setName(request.getName());

       candidateResearchProject.setNumber(request.getNumber());

       candidateResearchProject.setYear(request.getYear());

       candidateResearchProject.setUniName(request.getUniName());


        candidateResearchProject.setPhotoLink(request.getPhotoLink());

        candidateResearchProject.setApplication(application);

        candidateResearchProjectRepository.save(candidateResearchProject);

        application.addResearchProject(candidateResearchProject);

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Research Project has been loaded successfully!");

        return apiResponse;
    }

    public ApiResponse deleteReserchProject(Long applictionId, Long researchProjectId ) throws ApplicationException{

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ApplicationException("Application could not find by Id -> " + applictionId.toString());
        }

        CandidateResearchProject candidateResearchProject = candidateResearchProjectRepository.findByNameAndApplicationId(researchProjectId, applictionId);

        if (candidateResearchProject==null) {
            throw new ApplicationException("Research Project could not find by Id -> " + researchProjectId.toString());
        }

        candidateResearchProjectRepository.delete(candidateResearchProject);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Research Project delete successfully!");

        return apiResponse;
        
    }
}
