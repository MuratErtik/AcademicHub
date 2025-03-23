package com.yazlab.academichub.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.domain.USER_ROLE;
import com.yazlab.academichub.entities.DepartmentMenagerJobOffer;
import com.yazlab.academichub.entities.JobOffer;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.repository.DepartmentMenagerJobOfferRepository;
import com.yazlab.academichub.repository.UserRepository;
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

    public List<UserResponse> getUsersByRole(USER_ROLE role) {

        List<User> users = userRepository.findByUserRole(role);

        List<UserResponse> userResponses = users.stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());

        return userResponses;
    }

    public UserResponse convertToUserResponse(User user) {

        UserResponse userResponse = new UserResponse();

        userResponse.setName(user.getName());

        userResponse.setLastname(user.getLastname());

        userResponse.setDepartment(user.getDepartment());

        userResponse.setEmail(user.getEmail());

        userResponse.setMobileNo(user.getMobileNo());

        userResponse.setUserRole(user.getUserRole());

        return userResponse;
    }

    // ilana yonetici eklemem lazim!
    // ilan acildigi zaman yonetici de eklemek gerekir

    public List<DepartmentMenagerJobOffer> addManegerToJobOffer(USER_ROLE role, Long departmentId, JobOffer jobOffer) {

        List<User> usersToAddMenagerJobOffer = userRepository.findByUserRoleAndDepartment(role, departmentId);

        List<DepartmentMenagerJobOffer> departmentMenagerJobOffers = usersToAddMenagerJobOffer.stream()
                .map(user -> addToInnerTable(user, jobOffer))
                .collect(Collectors.toList());

        return departmentMenagerJobOffers;

    }

    public DepartmentMenagerJobOffer addToInnerTable(User user, JobOffer jobOffer) {

        DepartmentMenagerJobOffer departmentMenagerJobOffer = new DepartmentMenagerJobOffer();

        departmentMenagerJobOffer.setDepartmentMenager(user);

        departmentMenagerJobOffer.setJobOffer(jobOffer);

        return departmentMenagerJobOfferRepository.save(departmentMenagerJobOffer);
    }

    public User getAdminByEmail(String email) throws AdminException{

        String cleanedEmail = email.trim().replace("\"", "");

        User user = userRepository.findByEmail(cleanedEmail);

        System.out.println("*********************************************");
        System.out.println(email);
        System.out.println("*********************************************");


        

        if (user == null) {
            throw new AdminException("Admin could not find with email -> " + cleanedEmail);
        }

        return user;
    }

}
