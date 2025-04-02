package com.yazlab.academichub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yazlab.academichub.config.JwtProvider;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.entities.candidateDocuments.CandidateBook;
import com.yazlab.academichub.entities.candidateDocuments.CandidateCitation;
import com.yazlab.academichub.entities.candidateDocuments.CandidateSMA;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.CandidateBookException;
import com.yazlab.academichub.exception.CitationException;
import com.yazlab.academichub.exception.JobOfferException;
import com.yazlab.academichub.exception.SmaException;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.request.CreateArticleRequest;
import com.yazlab.academichub.response.ApiResponse;
import com.yazlab.academichub.response.JobOfferResponse;
import com.yazlab.academichub.service.CandidateArticleService;
import com.yazlab.academichub.service.CandidateBookService;
import com.yazlab.academichub.service.CandidateCitationService;
import com.yazlab.academichub.service.CandidateService;
import com.yazlab.academichub.service.CandidateSmaService;

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
}
