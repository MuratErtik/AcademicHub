package com.yazlab.academichub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yazlab.academichub.domain.USER_ROLE;
import com.yazlab.academichub.response.UserResponse;
import com.yazlab.academichub.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public List<UserResponse> getUsersByRole(@RequestParam USER_ROLE userRole) {

        return adminService.getUsersByRole(userRole);
    }

}
