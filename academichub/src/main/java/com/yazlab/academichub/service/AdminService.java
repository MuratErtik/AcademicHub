package com.yazlab.academichub.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Department;
import com.yazlab.academichub.entities.DepartmentMenagerJobOffer;
import com.yazlab.academichub.entities.FacultyGroup;
import com.yazlab.academichub.entities.JobOffer;
import com.yazlab.academichub.entities.MinMaxPointCriteria;
import com.yazlab.academichub.entities.Table3Action;
import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.entities.UserRole;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.repository.DepartmentMenagerJobOfferRepository;
import com.yazlab.academichub.repository.FacultyGroupRepository;
import com.yazlab.academichub.repository.MinMaxPointCriteriaRepository;
import com.yazlab.academichub.repository.PositionRepository;
import com.yazlab.academichub.repository.PublicationCriteriaRepository;
import com.yazlab.academichub.repository.Table3ActionRepository;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.repository.UserRoleRepository;
import com.yazlab.academichub.request.MinMaxPointCriteriaRequest;
import com.yazlab.academichub.request.PublicationCriteriaRequest;
import com.yazlab.academichub.request.Table3ActionRequest;
import com.yazlab.academichub.response.CandidateResponse;
import com.yazlab.academichub.response.MinMaxPointCriteriaResponse;
import com.yazlab.academichub.response.PublicationCriteriaResponse;
import com.yazlab.academichub.response.Table3ActionResponse;
import com.yazlab.academichub.response.UserResponse;

import com.yazlab.academichub.entities.Position;
import com.yazlab.academichub.entities.PublicationCriteria;

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
    
    private final Table3ActionRepository table3ActionRepository;
    private final FacultyGroupRepository facultyGroupRepository;
    private final PositionRepository positionRepository;
    private final MinMaxPointCriteriaRepository minMaxPointCriteriaRepository;
    private final PublicationCriteriaRepository publicationCriteriaRepository;


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

    // Table3Action CRUD operations
    // 1. Table3Action Create:
    public Table3ActionResponse createTable3Action(Table3ActionRequest request) {
    FacultyGroup facultyGroup = facultyGroupRepository.findById(request.getFacultyGroupId())
            .orElseThrow(() -> new RuntimeException("FacultyGroup not found"));

    Position position = positionRepository.findById(request.getPositionId())
            .orElseThrow(() -> new RuntimeException("Position not found"));

    Table3Action action = new Table3Action();
    action.setActionName(request.getActionName());
    action.setFacultyGroup(facultyGroup);
    action.setPosition(position);

    Table3Action saved = table3ActionRepository.save(action);

    return mapToResponse(saved);
    }


    // 2. Table3Action Update:
    public Table3ActionResponse updateTable3Action(Long id, Table3ActionRequest request) {
        Table3Action action = table3ActionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table3Action not found with id: " + id));
    
        action.setActionName(request.getActionName());
    
        if (request.getFacultyGroupId() != null) {
            FacultyGroup facultyGroup = facultyGroupRepository.findById(request.getFacultyGroupId())
                    .orElseThrow(() -> new RuntimeException("FacultyGroup not found"));
            action.setFacultyGroup(facultyGroup);
        }
    
        if (request.getPositionId() != null) {
            Position position = positionRepository.findById(request.getPositionId())
                    .orElseThrow(() -> new RuntimeException("Position not found"));
            action.setPosition(position);
        }
    
        Table3Action updated = table3ActionRepository.save(action);
        return mapToResponse(updated);
    }
    

    // 3. Table3Action Delete:
    public void deleteTable3Action(Long id) {
        if (!table3ActionRepository.existsById(id)) {
            throw new RuntimeException("Table3Action not found with id: " + id);
        }
    
        table3ActionRepository.deleteById(id);
    }
    
    
    // 4. Table3Action Get All:
    public List<Table3ActionResponse> getAllTable3Actions() {
        return table3ActionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    private Table3ActionResponse mapToResponse(Table3Action action) {
        Table3ActionResponse res = new Table3ActionResponse();
        res.setId(action.getTable3ActionId());
        res.setActionName(action.getActionName());
        res.setFacultyGroupName(action.getFacultyGroup().getFacultyGroupName());
        res.setPositionName(action.getPosition().getPositionName());
        return res;
    }    


    // MinMaxPointCriteria CRUD operations
    // 1. MinMaxPointCriteria Create:
    public MinMaxPointCriteriaResponse createMinMaxCriteria(MinMaxPointCriteriaRequest request) {
    Table3Action table3Action = table3ActionRepository.findById(request.getTable3ActionId())
            .orElseThrow(() -> new RuntimeException("Table3Action not found"));

    MinMaxPointCriteria criteria = new MinMaxPointCriteria();
    criteria.setTable3Action(table3Action);
    criteria.setMinPoint(request.getMinPoint());
    criteria.setMaxPoint(request.getMaxPoint());

    MinMaxPointCriteria saved = minMaxPointCriteriaRepository.save(criteria);
    return mapToMinMaxResponse(saved);
    }


    // 2. MinMaxPointCriteria Update:
    public MinMaxPointCriteriaResponse updateMinMaxCriteria(Long id, MinMaxPointCriteriaRequest request) {
        MinMaxPointCriteria criteria = minMaxPointCriteriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MinMaxPointCriteria not found"));
    
        criteria.setMinPoint(request.getMinPoint());
        criteria.setMaxPoint(request.getMaxPoint());
    
        if (request.getTable3ActionId() != null) {
            Table3Action action = table3ActionRepository.findById(request.getTable3ActionId())
                    .orElseThrow(() -> new RuntimeException("Table3Action not found"));
            criteria.setTable3Action(action);
        }
    
        MinMaxPointCriteria updated = minMaxPointCriteriaRepository.save(criteria);
        return mapToMinMaxResponse(updated);
    }


    // 3. MinMaxPointCriteria Delete:
    public void deleteMinMaxCriteria(Long id) {
        if (!minMaxPointCriteriaRepository.existsById(id)) {
            throw new RuntimeException("MinMaxPointCriteria not found");
        }
        minMaxPointCriteriaRepository.deleteById(id);
    }
    
    
    // 4. MinMaxPointCriteria Get All:
    public List<MinMaxPointCriteriaResponse> getAllMinMaxCriteria() {
        return minMaxPointCriteriaRepository.findAll().stream()
                .map(this::mapToMinMaxResponse)
                .collect(Collectors.toList());
    }
    

    private MinMaxPointCriteriaResponse mapToMinMaxResponse(MinMaxPointCriteria criteria) {
        MinMaxPointCriteriaResponse response = new MinMaxPointCriteriaResponse();
        response.setId(criteria.getMinMaxPointCriteriaId());
        response.setMinPoint(criteria.getMinPoint());
        response.setMaxPoint(criteria.getMaxPoint());
        response.setTable3ActionName(criteria.getTable3Action().getActionName());
        return response;
    }
    

    // 1. PublicationCriteria Create:
    public PublicationCriteriaResponse createPublicationCriteria(PublicationCriteriaRequest request) {
    Table3Action action = table3ActionRepository.findById(request.getTable3ActionId())
            .orElseThrow(() -> new RuntimeException("Table3Action not found"));

    PublicationCriteria criteria = new PublicationCriteria();
    criteria.setArticleCount(request.getArticleCount());
    criteria.setTable3Action(action);

    PublicationCriteria saved = publicationCriteriaRepository.save(criteria);
    return mapToPublicationResponse(saved);
    }


    // 2. PublicationCriteria Update:
    public PublicationCriteriaResponse updatePublicationCriteria(Long id, PublicationCriteriaRequest request) {
        PublicationCriteria criteria = publicationCriteriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PublicationCriteria not found"));
    
        criteria.setArticleCount(request.getArticleCount());
    
        if (request.getTable3ActionId() != null) {
            Table3Action action = table3ActionRepository.findById(request.getTable3ActionId())
                    .orElseThrow(() -> new RuntimeException("Table3Action not found"));
            criteria.setTable3Action(action);
        }
    
        PublicationCriteria updated = publicationCriteriaRepository.save(criteria);
        return mapToPublicationResponse(updated);
    }

    
    // 3. PublicationCriteria Delete:
    public void deletePublicationCriteria(Long id) {
        if (!publicationCriteriaRepository.existsById(id)) {
            throw new RuntimeException("PublicationCriteria not found");
        }
        publicationCriteriaRepository.deleteById(id);
    }
    

    // 4. PublicationCriteria Get All:
    public List<PublicationCriteriaResponse> getAllPublicationCriteria() {
        return publicationCriteriaRepository.findAll().stream()
                .map(this::mapToPublicationResponse)
                .collect(Collectors.toList());
    }

    
    private PublicationCriteriaResponse mapToPublicationResponse(PublicationCriteria criteria) {
        PublicationCriteriaResponse response = new PublicationCriteriaResponse();
        response.setId(criteria.getPublicationCriteriaId());
        response.setArticleCount(criteria.getArticleCount());
        response.setTable3ActionName(criteria.getTable3Action().getActionName());
        return response;
    }
    
}