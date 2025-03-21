package com.yazlab.academichub.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.domain.USER_ROLE;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.response.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// 1. Adminler giriş yaptıktan sonra ilan yonetim
// paneline erişir.
// 2. Yeni ilan ekleyebilir veya mevcut ilanları
// duzenleyebilir.
// 3. İ lanların başlangıç ve bitiş tarihleri, gerekli
// belgeler ve başvuru koşulları belirlenebilir.
// 4. İ lan başvurularını go ru ntu leyebilir ve
// gerektig inde yetkililere yo nlendirebilir.
public class AdminService {

    private final UserRepository userRepository;


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

        







  
}
