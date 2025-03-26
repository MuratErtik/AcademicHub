package com.yazlab.academichub.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.dto.MinMaxPointCriteriaDTO;
import com.yazlab.academichub.entities.AdminJobOffer;
import com.yazlab.academichub.entities.Department;
import com.yazlab.academichub.entities.JobOffer;
import com.yazlab.academichub.entities.MinMaxPointCriteria;
import com.yazlab.academichub.entities.Position;
import com.yazlab.academichub.entities.Table3Action;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.repository.AdminJobOfferRepository;
import com.yazlab.academichub.repository.DepartmentRepository;
import com.yazlab.academichub.repository.JobOfferRepository;
import com.yazlab.academichub.repository.MinMaxPointCriteriaRepository;
import com.yazlab.academichub.repository.PositionRepository;
import com.yazlab.academichub.request.CreateJobOfferRequest;

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

        return jobOffer;

    }

    private void addAdminToJobOffer(User user, JobOffer jobOffer) {

        AdminJobOffer adminJobOffer = new AdminJobOffer();

        adminJobOffer.setAdmin(user);

        adminJobOffer.setJobOffer(jobOffer);

        adminJobOfferRepository.save(adminJobOffer);

    }

    public JobOffer addCriteriaToJobOffer(Long jobOfferId) {

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

        return jobOfferRepository.save(jobOffer);
    }

}
