package com.yazlab.academichub.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yazlab.academichub.config.JwtProvider;
import com.yazlab.academichub.entities.JobOffer;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.exception.JobOfferException;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.request.CreateJobOfferRequest;
import com.yazlab.academichub.response.JobOfferResonseToAdmin;
import com.yazlab.academichub.response.UserResponse;
import com.yazlab.academichub.service.AdminService;
import com.yazlab.academichub.service.JobOfferService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/joboffer")
public class JobOfferController {

    private final JobOfferService jobOfferService;

    private final JwtProvider jwtProvider;

    private final AdminService adminService;

    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> createJobOffer(@RequestBody CreateJobOfferRequest request,
            @RequestHeader("Authorization") String jwt) throws AdminException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        String role = jwtProvider.getRolefromjwtByEmail(email);

        // System.out.println("************************************");
        // System.out.println(jwtProvider.getEmailFromJwtToken(jwt));
        // System.out.println(role);
        // System.out.println();

        // System.out.println("************************************");

        if (role.equals("ADMIN")) {

            UserResponse user = (UserResponse) adminService.getAdminByEmail(email);

            User user2 = userRepository.findByEmail(user.getEmail());

            JobOfferResonseToAdmin jobOffer = jobOfferService.createJobOffer(request, user2);

            return new ResponseEntity<>(jobOffer, HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // after the complete the method add the authorization for managers
    @GetMapping("/{jobOfferId}")
    public ResponseEntity<?> getJobOfferById(@RequestHeader("Authorization") String jwt, @PathVariable Long jobOfferId)
            throws AdminException, JobOfferException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        String role = jwtProvider.getRolefromjwtByEmail(email);

        if (role.equals("YONETICI") || role.equals("ADMIN")) {

            JobOfferResonseToAdmin jobOffer = jobOfferService.getJobOfferById(jobOfferId);

            return new ResponseEntity<>(jobOffer, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping
    public ResponseEntity<?> getJobOfferByPosition(@RequestHeader("Authorization") String jwt,
            @RequestParam String positionName)
            throws AdminException, JobOfferException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        String role = jwtProvider.getRolefromjwtByEmail(email);

        if (role.equals("YONETICI") || role.equals("ADMIN")) {

            List<JobOffer> jobOffers = jobOfferService.getJobOfferByPosition(positionName);

            return new ResponseEntity<>(jobOffers, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllJobOffer(@RequestHeader("Authorization") String jwt)
            throws AdminException, JobOfferException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        String role = jwtProvider.getRolefromjwtByEmail(email);

        if (role.equals("YONETICI") || role.equals("ADMIN")) {

            List<JobOffer> jobOffers = jobOfferService.getAllJobOffer();

            return new ResponseEntity<>(jobOffers, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/complete-job-offer/{jobOfferId}")
    public ResponseEntity<?> completeJobOffer(@RequestHeader("Authorization") String jwt, @PathVariable Long jobOfferId)
            throws AdminException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        String role = jwtProvider.getRolefromjwtByEmail(email);

        if (role.equals("YONETICI")) {

            JobOfferResonseToAdmin jobOffer = jobOfferService.addCriteriaToJobOffer(jobOfferId);

            return new ResponseEntity<>(jobOffer, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/{jobOfferId}")
    public ResponseEntity<Void> deleteProduct(@RequestHeader("Authorization") String jwt, @PathVariable Long jobOfferId)
            throws JobOfferException, AdminException {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        String role = jwtProvider.getRolefromjwtByEmail(email);

        if (role.equals("YONETICI") || role.equals("ADMIN")) {

            jobOfferService.deleteJobOffer(jobOfferId);

            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/{jobOfferId}")
    public ResponseEntity<JobOffer> updateProduct(@RequestHeader("Authorization") String jwt,
            @PathVariable Long jobOfferId, @RequestBody CreateJobOfferRequest request) {
        try {

            jobOfferService.updateJobOffer(jobOfferId, request);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (JobOfferException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }
}
