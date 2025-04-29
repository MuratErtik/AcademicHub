package com.yazlab.academichub.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.dto.MinMaxPointCriteriaDTO;
import com.yazlab.academichub.dto.PublicationCriteriaDTO;
import com.yazlab.academichub.entities.AdminJobOffer;
import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.Department;
import com.yazlab.academichub.entities.JobOffer;
import com.yazlab.academichub.entities.JuryApplication;
import com.yazlab.academichub.entities.MinMaxPointCriteria;
import com.yazlab.academichub.entities.Position;
import com.yazlab.academichub.entities.PublicationCriteria;
import com.yazlab.academichub.entities.Table3Action;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.entities.candidateDocuments.CandidateArticle;
import com.yazlab.academichub.entities.candidateDocuments.CandidateAuthor;
import com.yazlab.academichub.exception.JobOfferException;
import com.yazlab.academichub.repository.AdminJobOfferRepository;
import com.yazlab.academichub.repository.DepartmentRepository;
import com.yazlab.academichub.repository.JobOfferRepository;
import com.yazlab.academichub.repository.MinMaxPointCriteriaRepository;
import com.yazlab.academichub.repository.PositionRepository;
import com.yazlab.academichub.repository.PublicationCriteriaRepository;
import com.yazlab.academichub.request.CreateJobOfferRequest;
import com.yazlab.academichub.response.ApplicationResponse;
import com.yazlab.academichub.response.CandidateArticleResponse;
import com.yazlab.academichub.response.CandidateAuthorResponse;
import com.yazlab.academichub.response.JobOfferResonseToAdmin;
import com.yazlab.academichub.response.JuryApplicationResponse;
import com.yazlab.academichub.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    private final AdminJobOfferRepository adminJobOfferRepository;

    private final AdminService adminService;

    private final DepartmentRepository departmentRepository;

    private final PositionRepository positionRepository;

    private final MinMaxPointCriteriaRepository minMaxPointCriteriaRepository;

    private final PublicationCriteriaRepository publicationCriteriaRepository;

    // create new one DONE
    // delete one DONE
    // update one
    // findjobofferbyId DONE
    // findjobofferbyposition vs... DONE

    public JobOfferResonseToAdmin createJobOffer(CreateJobOfferRequest request, User user) {

        JobOffer jobOffer = new JobOffer();

        Department department = departmentRepository.findByDepartmentName(request.getDepartmentName());

        jobOffer.setTitle(request.getTitle());

        jobOffer.setDescription(request.getDescription());

        jobOffer.setDepartment(department);

        jobOffer.setStartDate(request.getStartDate());

        jobOffer.setEndDate(request.getEndDate());

        Position position = positionRepository.findByPositionName(request.getPositionName());

        jobOffer.setPosition(position);

        jobOfferRepository.save(jobOffer);

        // save admin who has created the offer. after than add to all menagers who have
        // the department to relative dep

        addAdminToJobOffer(user, jobOffer);

        // System.out.println("**************************************************************");
        // // System.out.println(userRole);
        // // System.out.println(roleId);
        // System.out.println(request.getDepartmentName());
        // System.out.println(department.getDepartmentName());
        // System.out.println(department.getDepartmentId());

        // System.out.println("**************************************************************");

        adminService.addManegerToJobOffer(department.getDepartmentId(), jobOffer);

        return convertResonseToAdmin(jobOffer);

    }

    private void addAdminToJobOffer(User user, JobOffer jobOffer) {

        AdminJobOffer adminJobOffer = new AdminJobOffer();

        adminJobOffer.setAdmin(user);

        adminJobOffer.setJobOffer(jobOffer);

        adminJobOfferRepository.save(adminJobOffer);

    }

    public JobOfferResonseToAdmin addCriteriaToJobOffer(Long jobOfferId) {

        JobOffer jobOffer = jobOfferRepository.findByJobOfferId(jobOfferId);

        Long positionId = jobOffer.getPosition().getPositionId();

        List<MinMaxPointCriteriaDTO> criteriaDTOs = minMaxPointCriteriaRepository
                .findAllWithActionDetails(positionId);

        Set<MinMaxPointCriteria> criteriaSet = criteriaDTOs.stream()
                .map(dto -> {
                    MinMaxPointCriteria criteria = new MinMaxPointCriteria();
                    criteria.setMinMaxPointCriteriaId(dto.getMinMaxPointCriteriaId());
                    criteria.setMinPoint(dto.getMinPoint());
                    criteria.setMaxPoint(dto.getMaxPoint());

                    Table3Action action = new Table3Action();
                    action.setActionName(dto.getActionName());
                    criteria.setTable3Action(action);

                    return criteria;
                })
                .collect(Collectors.toSet());

        jobOffer.setMinMaxPointCriterias(criteriaSet);

        List<PublicationCriteriaDTO> publicationCriteriaDTOs = publicationCriteriaRepository
                .findAllWithActionDetails(positionId);

        Set<PublicationCriteria> publicationCriteriaSet = publicationCriteriaDTOs.stream()
                .map(dto -> {
                    PublicationCriteria criteria = new PublicationCriteria();
                    criteria.setPublicationCriteriaId(dto.getPublicationCriteriaId());
                    criteria.setArticleCount(dto.getArticleCount());

                    Table3Action action = new Table3Action();
                    action.setActionName(dto.getActionName());
                    criteria.setTable3Action(action);

                    return criteria;
                })
                .collect(Collectors.toSet());

        jobOffer.setPublicationCriterias(publicationCriteriaSet);

        jobOfferRepository.save(jobOffer);

        return convertResonseToAdmin(jobOffer);
    }

    public JobOfferResonseToAdmin getJobOfferById(Long jobOfferId) throws JobOfferException {

        JobOffer jobOffer = jobOfferRepository.findByJobOfferId(jobOfferId);

        if (jobOffer == null) {
            throw new JobOfferException("The product has not been found with id -> " + jobOfferId.toString());
        } else {
            return convertResonseToAdmin(jobOffer);
        }

    }

    public JobOfferResonseToAdmin convertResonseToAdmin(JobOffer jobOffer) {

        JobOfferResonseToAdmin jobOfferResonseToAdmin = new JobOfferResonseToAdmin();

        jobOfferResonseToAdmin.setJobOfferId(jobOffer.getJobOfferId());

        jobOfferResonseToAdmin.setTitle(jobOffer.getTitle());

        jobOfferResonseToAdmin.setDescription(jobOffer.getDescription());

        jobOfferResonseToAdmin.setStartDate(jobOffer.getStartDate());

        jobOfferResonseToAdmin.setEndDate(jobOffer.getEndDate());

        jobOfferResonseToAdmin.setDepartmentName(jobOffer.getDepartment().getDepartmentName());

        jobOfferResonseToAdmin.setPositionName(jobOffer.getPosition().getPositionName());

        jobOfferResonseToAdmin.setMinMaxPointCriterias(jobOffer.getMinMaxPointCriterias());

        jobOfferResonseToAdmin.setPublicationCriterias(jobOffer.getPublicationCriterias());

        Set<ApplicationResponse> applicationResponses = convert(jobOffer.getApplications());

        jobOfferResonseToAdmin.setApplications(applicationResponses);

        jobOfferResonseToAdmin.setApplicationCount(jobOffer.getApplications().size());

        return jobOfferResonseToAdmin;

    }

    public Set<ApplicationResponse> convert(Set<Application> applications) {
        return applications.stream()
                .map(this::convertSingleApplication)
                .collect(Collectors.toSet());
    }

    private ApplicationResponse convertSingleApplication(Application application) {
        ApplicationResponse applicationResponse = new ApplicationResponse();

        applicationResponse.setApplicationId(application.getApplicationId());

        if (application.getCandidate() != null) {
            applicationResponse.setUserId(application.getCandidate().getUserId());
        }

        applicationResponse.setApplicationDate(application.getApplicationDate());

        if (application.getApplicationStatus() != null) {
            applicationResponse.setApplicationStatusName(application.getApplicationStatus().getApplicationStatus());
        }

        if (application.getArticles() != null) {

            Set<CandidateArticleResponse> candidateArticleResponses = convertArticle(application.getArticles());

            applicationResponse.setArticles(candidateArticleResponses);
        } else {
            applicationResponse.setArticles(new HashSet<>());
        }

        if (application.getSmas() != null) {
            applicationResponse.setSmas(application.getSmas());
        } else {
            applicationResponse.setSmas(new HashSet<>());
        }

        if (application.getBooks() != null) {
            applicationResponse.setBooks(application.getBooks());
        } else {
            applicationResponse.setBooks(new HashSet<>());
        }

        if (application.getCitations() != null) {
            applicationResponse.setCitations(application.getCitations());
        } else {
            applicationResponse.setCitations(new HashSet<>());
        }

        if (application.getEducationActions() != null) {
            applicationResponse.setEducationActions(application.getEducationActions());
        } else {
            applicationResponse.setEducationActions(new HashSet<>());
        }

        if (application.getThesisSupervisions() != null) {
            applicationResponse.setThesisSupervisions(application.getThesisSupervisions());
        } else {
            applicationResponse.setThesisSupervisions(new HashSet<>());
        }

        if (application.getPatents() != null) {
            applicationResponse.setPatents(application.getPatents());
        } else {
            applicationResponse.setPatents(new HashSet<>());
        }

        if (application.getResearchProjects() != null) {
            applicationResponse.setResearchProjects(application.getResearchProjects());
        } else {
            applicationResponse.setResearchProjects(new HashSet<>());
        }
        
        if (application.getEditorships() != null) {
            applicationResponse.setEditorships(application.getEditorships());
        } else {
            applicationResponse.setEditorships(new HashSet<>());
        }

        if (application.getAwards() != null) {
            applicationResponse.setAwards(application.getAwards());
        } else {
            applicationResponse.setAwards(new HashSet<>());
        }

        if (application.getCandidateContributionActivities() != null) {
            applicationResponse.setContributionActivities(application.getCandidateContributionActivities());
        } else {
            applicationResponse.setContributionActivities(new HashSet<>());
        }

        if (application.getJuryApplications() != null) {

            applicationResponse.setJuryApplications(convertJuryApp(application.getJuryApplications()));
        } else {
            applicationResponse.setJuryApplications(new HashSet<>());
        }

        return applicationResponse;
    }

    public Set<CandidateArticleResponse> convertArticle(Set<CandidateArticle> articles) {
        return articles.stream()
                .map(this::convertSingleArticleResponse)
                .collect(Collectors.toSet());
    }

    public CandidateArticleResponse convertSingleArticleResponse(CandidateArticle candidateArticle) {

        CandidateArticleResponse candidateArticleResponse = new CandidateArticleResponse();

        candidateArticleResponse.setCandidateArticleId(candidateArticle.getCandidateArticleId());

        candidateArticleResponse.setArticleName(candidateArticle.getArticleName());

        candidateArticleResponse.setArticleCategory(candidateArticle.getArticleCategory());

        candidateArticleResponse.setArticleTypeName(candidateArticle.getArticleType().getArticleTypeName());

        candidateArticleResponse.setAuthorCount(candidateArticle.getAuthorCount());

        Set<CandidateAuthorResponse> authorResponses = convertAuthor(candidateArticle.getAuthors());

        candidateArticleResponse.setAuthors(authorResponses);

        candidateArticleResponse.setPhotoLink(candidateArticle.getPhotoLink());

        return candidateArticleResponse;

    }

    public Set<JuryApplicationResponse> convertJuryApp(Set<JuryApplication> juries){
        return juries.stream()
                .map(this::convertSingleEvalaution)
                .collect(Collectors.toSet());
    }

    public JuryApplicationResponse convertSingleEvalaution(JuryApplication juryApplication){

        JuryApplicationResponse juryApplicationResponse = new JuryApplicationResponse();

        juryApplicationResponse.setApplicationId(juryApplication.getApplication().getApplicationId());

        juryApplicationResponse.setJuryevalutationResponse(juryApplication.getJuryevalutationResponse());

        juryApplicationResponse.setApproved(juryApplication.isApproved());

        juryApplicationResponse.setId(juryApplication.getId());

        UserResponse jury = convertJuryToResponse(juryApplication.getJury());

        jury.setUserRole("JURI");

        jury.setUserId(juryApplication.getJury().getUserId());

        juryApplicationResponse.setJury(jury);

        juryApplicationResponse.setJuryevalutationResponse(juryApplication.getJuryevalutationResponse());

        return juryApplicationResponse;
        
    }

    public Set<UserResponse> convertJuries(Set<User> juries){
        return juries.stream()
                .map(this::convertJuryToResponse)
                .collect(Collectors.toSet());
    }

    public UserResponse convertJuryToResponse(User jury){

        UserResponse userResponse = new UserResponse();

        userResponse.setDepartmentName(jury.getDepartment().getDepartmentName());

        userResponse.setEmail(jury.getEmail());

        userResponse.setLastname(jury.getLastname());

        userResponse.setMobileNo(jury.getMobileNo());

        userResponse.setName(jury.getName());

        // userResponse.setUserId(jury.getUserId());

        // userResponse.setUserRole(jury.getUserRole().getUserRole());

        return userResponse;
    }

    public Set<CandidateAuthorResponse> convertAuthor(Set<CandidateAuthor> authors) {
        return authors.stream()
                .map(this::convertSingleAuthorResponse)
                .collect(Collectors.toSet());
    }

    public CandidateAuthorResponse convertSingleAuthorResponse(CandidateAuthor candidateAuthor) {

        CandidateAuthorResponse candidateAuthorResponse = new CandidateAuthorResponse();

        candidateAuthorResponse.setCandidateArticleId(candidateAuthor.getCandidateArticleId());

        candidateAuthorResponse.setName(candidateAuthor.getName());

        candidateAuthorResponse.setSurname(candidateAuthor.getSurname());

        candidateAuthorResponse.setAuthorTypeName(candidateAuthor.getAuthorType().getAuthorTypeName());

        return candidateAuthorResponse;

    }

    public List<JobOffer> getJobOfferByPosition(String positionName) {

        Position position = positionRepository.findByPositionName(positionName);

        String positionId = position.getPositionId().toString();

        return jobOfferRepository.findByPosition(positionId);
    }

    public List<JobOffer> getAllJobOffer() {

        return jobOfferRepository.findAll();
    }

    public void deleteJobOffer(Long jobOfferId) {

        JobOffer jobOffer = jobOfferRepository.findByJobOfferId(jobOfferId);

        jobOfferRepository.delete(jobOffer);
    }

    public JobOffer updateJobOffer(Long jobOfferId, CreateJobOfferRequest request) throws JobOfferException {

        JobOffer existJobOffer = jobOfferRepository.findByJobOfferId(jobOfferId);

        if (existJobOffer.getTitle() != null) {
            existJobOffer.setTitle(request.getTitle());
        }

        if (existJobOffer.getDescription() != null) {
            existJobOffer.setDescription(request.getDescription());
        }

        if (existJobOffer.getDescription() != null) {
            existJobOffer.setDescription(request.getDescription());
        }

        if (existJobOffer.getStartDate() != null) {
            existJobOffer.setStartDate(request.getStartDate());
        }

        if (existJobOffer.getEndDate() != null) {
            existJobOffer.setEndDate(request.getEndDate());
        }

        Department department = departmentRepository.findByDepartmentName(request.getDepartmentName());

        if (existJobOffer.getDepartment() != null) {
            existJobOffer.setDepartment(department);
        }

        Position position = positionRepository.findByPositionName(request.getPositionName());

        if (existJobOffer.getPosition() != null) {
            existJobOffer.setPosition(position);
        }

        adminService.addManegerToJobOffer(department.getDepartmentId(), existJobOffer);

        return jobOfferRepository.save(existJobOffer);

    }
}
