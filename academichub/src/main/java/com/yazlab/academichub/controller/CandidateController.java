package com.yazlab.academichub.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yazlab.academichub.config.JwtProvider;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.entities.candidateDocuments.CandidateAward;
import com.yazlab.academichub.entities.candidateDocuments.CandidateBook;
import com.yazlab.academichub.entities.candidateDocuments.CandidateCitation;
import com.yazlab.academichub.entities.candidateDocuments.CandidateContributionActivity;
import com.yazlab.academichub.entities.candidateDocuments.CandidateEditorship;
import com.yazlab.academichub.entities.candidateDocuments.CandidateEducationAction;
import com.yazlab.academichub.entities.candidateDocuments.CandidatePatent;
import com.yazlab.academichub.entities.candidateDocuments.CandidateResearchProject;
import com.yazlab.academichub.entities.candidateDocuments.CandidateSMA;
import com.yazlab.academichub.entities.candidateDocuments.CandidateThesisSupervision;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.AwardException;
import com.yazlab.academichub.exception.CandidateBookException;
import com.yazlab.academichub.exception.CitationException;
import com.yazlab.academichub.exception.EducationActionException;
import com.yazlab.academichub.exception.JobOfferException;
import com.yazlab.academichub.exception.PatentException;
import com.yazlab.academichub.exception.ResearchProjectException;
import com.yazlab.academichub.exception.SmaException;
import com.yazlab.academichub.exception.ThesisSupervisionException;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.request.CreateArticleRequest;
import com.yazlab.academichub.response.ApiResponse;
import com.yazlab.academichub.response.ApplicationResponseToCandidate;
import com.yazlab.academichub.response.JobOfferResponse;
import com.yazlab.academichub.service.CandidateArticleService;
import com.yazlab.academichub.service.CandidateAwardService;
import com.yazlab.academichub.service.CandidateBookService;
import com.yazlab.academichub.service.CandidateCitationService;
import com.yazlab.academichub.service.CandidateContributionActivityService;
import com.yazlab.academichub.service.CandidateEditorshipService;
import com.yazlab.academichub.service.CandidatePatentService;
import com.yazlab.academichub.service.CandidateResearchProjectService;
import com.yazlab.academichub.service.CandidateService;
import com.yazlab.academichub.service.CandidateSmaService;
import com.yazlab.academichub.service.EducationActionService;
import com.yazlab.academichub.service.ThesisSupervisionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    private final CandidateArticleService candidateArticleService;

    private final CandidateSmaService candidateSmaService;

    private final CandidateBookService candidateBookService;

    private final CandidateCitationService candidateCitationService;

    private final EducationActionService educationActionService;

    private final ThesisSupervisionService thesisSupervisionService;

    private final CandidatePatentService patentService;

    private final CandidateResearchProjectService projectService;

    private final CandidateEditorshipService editorshipService;

    private final CandidateAwardService awardService;

    private final CandidateContributionActivityService candidateContributionActivityService;

    @GetMapping("/getAllJobOffer")
    public ResponseEntity<List<JobOfferResponse>> getAJobOffers(@RequestHeader("Authorization") String jwt)
            throws AdminException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        String role = jwtProvider.getRolefromjwtByEmail(email);

        if (role.equals("ADAY")) {
            List<JobOfferResponse> jobOffers = candidateService.listAllOffers();

            return ResponseEntity.ok(jobOffers);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{jobOfferId}/start-application")
    public ResponseEntity<ApiResponse> createApplication(@RequestHeader("Authorization") String jwt,
            @PathVariable Long jobOfferId) throws AdminException, JobOfferException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);

        User user = userRepository.findByEmail(email);

        Long userId = (Long)user.getUserId();

        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateService.createApplication(jobOfferId, userId);

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{applicationId}/add-article")
    public ResponseEntity<ApiResponse> addArticle(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody CreateArticleRequest request) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateArticleService.addArticle(applicationId, request);

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{applicationId}/delete-article/{articleId}")
    public ResponseEntity<ApiResponse> deleteArticle(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @PathVariable Long articleId ) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateArticleService.deleteArticle(applicationId, articleId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{applicationId}/add-sma")
    public ResponseEntity<ApiResponse> addSma(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody CandidateSMA request) throws AdminException, ApplicationException, SmaException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateSmaService.addSma(applicationId, request);

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{applicationId}/delete-sma/{smaId}")
    public ResponseEntity<ApiResponse> deleteSma(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @PathVariable Long smaId ) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateSmaService.deleteSma(applicationId, smaId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{applicationId}/add-book")
    public ResponseEntity<ApiResponse> addBook(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody CandidateBook request) throws AdminException, ApplicationException, CandidateBookException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateBookService.addBook(applicationId, request);

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{applicationId}/delete-book/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @PathVariable Long bookId ) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateBookService.deleteBook(applicationId, bookId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
    

    @PostMapping("/{applicationId}/add-citation")
    public ResponseEntity<ApiResponse> addCitation(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody CandidateCitation request) throws AdminException, ApplicationException, CitationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateCitationService.addCitation(applicationId, request);

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{applicationId}/delete-citation/{citationId}")
    public ResponseEntity<ApiResponse> deleteCitation(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @PathVariable Long citationId ) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateCitationService.deleteCitation(applicationId, citationId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{applicationId}/add-education-action")
    public ResponseEntity<ApiResponse> addEducationAction(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody CandidateEducationAction request) throws AdminException, ApplicationException, EducationActionException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = educationActionService.addEducationAction(applicationId, request);

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{applicationId}/delete-education-action/{educationActionId}")
    public ResponseEntity<ApiResponse> deleteEducationAction(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @PathVariable Long educationActionId ) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = educationActionService.deleteEducationAction(applicationId, educationActionId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{applicationId}/add-thesis-superivision")
    public ResponseEntity<ApiResponse> addThesisSupervision(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody CandidateThesisSupervision request) throws AdminException, ApplicationException, ThesisSupervisionException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = thesisSupervisionService.addThesisSupervision(applicationId, request);

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{applicationId}/delete-thesis-supervision/{thesisSuperivisonId}")
    public ResponseEntity<ApiResponse> deleteThesisSupervision(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @PathVariable Long thesisSuperivisonId ) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = thesisSupervisionService.deleteThesisSupervision(applicationId, thesisSuperivisonId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{applicationId}/add-research-project")
    public ResponseEntity<ApiResponse> addResearchProject(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody CandidateResearchProject request) throws AdminException, ApplicationException, ResearchProjectException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = projectService.addResearchProject(applicationId, request);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{applicationId}/delete-research-project/{projectId}")
    public ResponseEntity<ApiResponse> deleteResearchProject(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @PathVariable Long projectId ) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse =projectService.deleteReserchProject(applicationId, projectId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{applicationId}/add-patent")
    public ResponseEntity<ApiResponse> addPatent(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody CandidatePatent request) throws AdminException, ApplicationException, PatentException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = patentService.addPatent(applicationId, request);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{applicationId}/delete-patent/{patentId}")
    public ResponseEntity<ApiResponse> deletePatent(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @PathVariable Long patentId ) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = patentService.deletePatent(applicationId, patentId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{applicationId}/add-editorship")
    public ResponseEntity<ApiResponse> addEditorship(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody CandidateEditorship request) throws AdminException, ApplicationException, EducationActionException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = editorshipService.addEditorship(applicationId, request);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{applicationId}/delete-editorship/{editorshipId}")
    public ResponseEntity<ApiResponse> deleteEditorship(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @PathVariable Long editorshipId ) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = editorshipService.deleteEditorship(applicationId, editorshipId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{applicationId}/add-award")
    public ResponseEntity<ApiResponse> addAward(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody CandidateAward request) throws AdminException, ApplicationException, AwardException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = awardService.addAward(applicationId, request);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{applicationId}/delete-award/{awardId}")
    public ResponseEntity<ApiResponse> deleteAward(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @PathVariable Long awardId ) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = awardService.deleteAward(applicationId, awardId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/{applicationId}/add-ca")
    public ResponseEntity<ApiResponse> addCa(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @RequestBody CandidateContributionActivity request) throws AdminException, ApplicationException, AwardException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateContributionActivityService.addca(applicationId, request);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{applicationId}/delete-ca/{caId}")
    public ResponseEntity<ApiResponse> deleteCa(@RequestHeader("Authorization") String jwt,
            @PathVariable Long applicationId, @PathVariable Long caId ) throws AdminException, ApplicationException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateContributionActivityService.deleteCa(applicationId, caId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/{applicationId}/complete-application")
    public ResponseEntity<ApiResponse> completeApplication(@RequestHeader("Authorization") String jwt,@PathVariable Long applicationId) throws AdminException{

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            ApiResponse apiResponse = candidateService.completeApplication(applicationId);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/get-all-application")
    public ResponseEntity<Set<ApplicationResponseToCandidate>> getAllApplication(@RequestHeader("Authorization") String jwt) throws AdminException{

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        User user = userRepository.findByEmail(email);

        String role = jwtProvider.getRolefromjwtByEmail(email);



        if (role.equals("ADAY")) {
            Set<ApplicationResponseToCandidate> apiResponse = candidateService.getAllApplication(user.getUserId());

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

}
