package com.yazlab.academichub.service;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.domain.USER_ROLE;
import com.yazlab.academichub.entities.AdminJobOffer;
import com.yazlab.academichub.entities.Department;
import com.yazlab.academichub.entities.JobOffer;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.repository.AdminJobOfferRepository;
import com.yazlab.academichub.repository.DepartmentRepository;
import com.yazlab.academichub.repository.JobOfferRepository;
import com.yazlab.academichub.request.CreateJobOfferRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    private final AdminJobOfferRepository adminJobOfferRepository;

    private final AdminService adminService;

    private final DepartmentRepository departmentRepository;

    // create new one
    // delete one
    // update one
    // findjobofferbyId
    // findjobofferbyposition vs...
    // searchjobOffer
    // getAllProduct

    public JobOffer createJobOffer(CreateJobOfferRequest request, User user) {

        JobOffer jobOffer = new JobOffer();

        Department department = departmentRepository.findByDepartmentName(request.getDepartmentName());

        jobOffer.setDepartment(department);

        jobOffer.setStartDate(request.getStartDate());

        jobOffer.setEndDate(request.getEndDate());

        jobOfferRepository.save(jobOffer);

        // save admin who has created the offer. after than add to all menagers who have
        // the department to relative dep

        addAdminToJobOffer(user, jobOffer);

        adminService.addManegerToJobOffer(USER_ROLE.DEPARTMENT_MANAGER, department.getDepartmentId(), jobOffer);

        return jobOffer;

    }

    private void addAdminToJobOffer(User user, JobOffer jobOffer) {

        AdminJobOffer adminJobOffer = new AdminJobOffer();

        adminJobOffer.setAdmin(user);

        adminJobOffer.setJobOffer(jobOffer);

        adminJobOfferRepository.save(adminJobOffer);

    }

}
