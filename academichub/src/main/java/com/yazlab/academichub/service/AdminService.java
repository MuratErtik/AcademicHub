package com.yazlab.academichub.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Department;
import com.yazlab.academichub.entities.DepartmentMenagerJobOffer;
import com.yazlab.academichub.entities.JobOffer;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.entities.UserRole;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.repository.DepartmentMenagerJobOfferRepository;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.repository.UserRoleRepository;
import com.yazlab.academichub.response.CandidateResponse;
import com.yazlab.academichub.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// 1. Adminler giriş yaptıktan sonra ilan yonetim
// paneline erişir.
// 2. Yeni ilan ekleyebilir veya mevcut ilanları
// duzenleyebilir.
// 3. İlanların başlangıç ve bitiş tarihleri, gerekli
// belgeler ve başvuru koşulları belirlenebilir.
// 4. İlan başvurularını goruntuleyebilir ve
// gerektiginde yetkililere yonlendirebilir.
public class AdminService {

    private final UserRepository userRepository;

    private final DepartmentMenagerJobOfferRepository departmentMenagerJobOfferRepository;

    private final UserRoleRepository userRoleRepository;

    public List<?> getUsersByRole(String role) {

        UserRole userRole = userRoleRepository.findUserRoleByUserRole(role);

        List<User> users = userRepository.findByUserRole(userRole);

        if (role.equals("ADAY")) {

            List<CandidateResponse> candidateResponse = users.stream()
                    .map(this::convertToCandidateResponse)
                    .collect(Collectors.toList());

            return candidateResponse;


        } else {
            List<UserResponse> userResponses = users.stream()
                    .map(this::convertToUserResponse)
                    .collect(Collectors.toList());

            return userResponses;
        }

    }

    public UserResponse convertToUserResponse(User user) {

        UserResponse userResponse = new UserResponse();

        userResponse.setUserId(user.getUserId());

        userResponse.setName(user.getName());

        userResponse.setLastname(user.getLastname());

        userResponse.setDepartmentName(Optional.ofNullable(user.getDepartment())
                .map(Department::getDepartmentName)
                .orElse(null));

        userResponse.setEmail(user.getEmail());

        userResponse.setMobileNo(user.getMobileNo());

        userResponse.setUserRole(user.getUserRole().getUserRole());

        return userResponse;
    }

    public CandidateResponse convertToCandidateResponse(User user) {

        CandidateResponse candidateResponse = new CandidateResponse();

        candidateResponse.setUserId(user.getUserId());

        candidateResponse.setName(user.getName());

        candidateResponse.setLastname(user.getLastname());

        candidateResponse.setEmail(user.getEmail());

        candidateResponse.setMobileNo(user.getMobileNo());

        candidateResponse.setUserRole(user.getUserRole().getUserRole());

        // candidateResponse.setApplications(); handle it later!!!

        return candidateResponse;
    }

    // ilana yonetici eklemem lazim!
    // ilan acildigi zaman yonetici de eklemek gerekir

    public void addManegerToJobOffer(Long departmentId, JobOffer jobOffer) {

        List<User> usersToAddMenagerJobOffer = userRepository.findByUserRoleAndDepartment(2L, departmentId);

        usersToAddMenagerJobOffer.stream()
                .map(user -> addToInnerTable(user, jobOffer))
                .collect(Collectors.toList());

    }

    public DepartmentMenagerJobOffer addToInnerTable(User user, JobOffer jobOffer) {

        DepartmentMenagerJobOffer departmentMenagerJobOffer = new DepartmentMenagerJobOffer();

        departmentMenagerJobOffer.setDepartmentMenager(user);

        departmentMenagerJobOffer.setJobOffer(jobOffer);

        return departmentMenagerJobOfferRepository.save(departmentMenagerJobOffer);
    }

    public Object getAdminByEmail(String email) throws AdminException {

        String cleanedEmail = email.trim().replace("\"", "");

        User user = userRepository.findByEmail(cleanedEmail);

        if (user == null) {
            throw new AdminException("Admin could not find with email -> " + cleanedEmail);
        }

        if (user.getUserRole().getUserRole().equals("ADAY")) {
            
            CandidateResponse candidateResponse = convertToCandidateResponse(user);

            return candidateResponse;
        }

        else{

            UserResponse userResponse = convertToUserResponse(user);

            return userResponse;

        }

        

        // System.out.println("*********************************************");
        // System.out.println(email);
        // System.out.println("*********************************************");



    }

}
