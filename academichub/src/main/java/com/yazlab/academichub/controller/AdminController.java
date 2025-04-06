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
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.request.MinMaxPointCriteriaRequest;
import com.yazlab.academichub.request.PublicationCriteriaRequest;
import com.yazlab.academichub.request.Table3ActionRequest;
import com.yazlab.academichub.response.MinMaxPointCriteriaResponse;
import com.yazlab.academichub.response.PublicationCriteriaResponse;
import com.yazlab.academichub.response.Table3ActionResponse;
import com.yazlab.academichub.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final JwtProvider jwtProvider;


    @GetMapping("/users")
    public List<?> getUsersByRole(@RequestParam String userRole) {

        return adminService.getUsersByRole(userRole);
    }

    @GetMapping("/getadminbyemail")
    public Object getUserByEmail(@RequestBody String email) throws AdminException {

        return adminService.getAdminByEmail(email);
    }

    // Table3Action CRUD operations
    @PostMapping("/table3actions")
    public ResponseEntity<?> createTable3Action(@RequestHeader("Authorization") String jwt,
                                                @RequestBody Table3ActionRequest request) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                Table3ActionResponse response = adminService.createTable3Action(request);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }

    @PutMapping("/table3actions/{id}")
    public ResponseEntity<?> updateTable3Action(@RequestHeader("Authorization") String jwt,
                                                @PathVariable Long id,
                                                @RequestBody Table3ActionRequest request) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                Table3ActionResponse response = adminService.updateTable3Action(id, request);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }

    @DeleteMapping("/table3actions/{id}")
    public ResponseEntity<?> deleteTable3Action(@RequestHeader("Authorization") String jwt,
                                                @PathVariable Long id) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                adminService.deleteTable3Action(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }

    @GetMapping("/table3actions")
    public ResponseEntity<?> getAllTable3Actions(@RequestHeader("Authorization") String jwt) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                List<Table3ActionResponse> list = adminService.getAllTable3Actions();
                return ResponseEntity.ok(list);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }


    // MinMaxPointCriteria CRUD operations
    @PostMapping("/minmaxcriteria")
    public ResponseEntity<?> createMinMaxCriteria(@RequestHeader("Authorization") String jwt,
                                                @RequestBody MinMaxPointCriteriaRequest request) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                MinMaxPointCriteriaResponse response = adminService.createMinMaxCriteria(request);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }

    @PutMapping("/minmaxcriteria/{id}")
    public ResponseEntity<?> updateMinMaxCriteria(@RequestHeader("Authorization") String jwt,
                                                @PathVariable Long id,
                                                @RequestBody MinMaxPointCriteriaRequest request) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                MinMaxPointCriteriaResponse response = adminService.updateMinMaxCriteria(id, request);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }

    @DeleteMapping("/minmaxcriteria/{id}")
    public ResponseEntity<?> deleteMinMaxCriteria(@RequestHeader("Authorization") String jwt,
                                                @PathVariable Long id) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                adminService.deleteMinMaxCriteria(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }

    @GetMapping("/minmaxcriteria")
    public ResponseEntity<?> getAllMinMaxCriteria(@RequestHeader("Authorization") String jwt) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                List<MinMaxPointCriteriaResponse> list = adminService.getAllMinMaxCriteria();
                return ResponseEntity.ok(list);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }


    // PublicationCriteria CRUD operations
    @PostMapping("/publicationcriteria")
    public ResponseEntity<?> createPublicationCriteria(@RequestHeader("Authorization") String jwt,
                                                    @RequestBody PublicationCriteriaRequest request) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                PublicationCriteriaResponse response = adminService.createPublicationCriteria(request);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }

    @PutMapping("/publicationcriteria/{id}")
    public ResponseEntity<?> updatePublicationCriteria(@RequestHeader("Authorization") String jwt,
                                                    @PathVariable Long id,
                                                    @RequestBody PublicationCriteriaRequest request) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                PublicationCriteriaResponse response = adminService.updatePublicationCriteria(id, request);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }

    @DeleteMapping("/publicationcriteria/{id}")
    public ResponseEntity<?> deletePublicationCriteria(@RequestHeader("Authorization") String jwt,
                                                    @PathVariable Long id) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                adminService.deletePublicationCriteria(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }

    @GetMapping("/publicationcriteria")
    public ResponseEntity<?> getAllPublicationCriteria(@RequestHeader("Authorization") String jwt) {
        try {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            String role = jwtProvider.getRolefromjwtByEmail(email);

            if ("YONETICI".equals(role)) {
                List<PublicationCriteriaResponse> list = adminService.getAllPublicationCriteria();
                return ResponseEntity.ok(list);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (AdminException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Yetki kontrolü sırasında hata: " + e.getMessage());
        }
    }

}