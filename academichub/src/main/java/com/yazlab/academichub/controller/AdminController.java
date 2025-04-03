package com.yazlab.academichub.controller;

import com.yazlab.academichub.entities.*;
import com.yazlab.academichub.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;
    private final RoleRepository roleRepository;
    private final ArticleRepository articleRepository;
    private final BookRepository bookRepository;
    private final PatentRepository patentRepository;
    private final MeetingActivityRepository meetingActivityRepository;
    private final SupportingDocumentRepository supportingDocumentRepository;
    private final Table3Repository table3Repository;
    private final Table4Repository table4Repository;
    private final ThesisSupervisionRepository thesisSupervisionRepository;
    private final JobOfferRepository jobOfferRepository;
    private final ApplicationRepository applicationRepository;
    private final CitationRepository citationRepository;
    private final AuthorTypeRepository authorTypeRepository;
    private final AwardRepository awardRepository;
    private final ArticleTypeRepository articleTypeRepository;
    private final CandidateDocumentRepository candidateDocumentRepository;
    private final ContributionActivitiesRepository contributionActivitiesRepository;
    private final DepartmentManagerJobRepository departmentManagerJobRepository;
    private final EditorshipRepository editorshipRepository;
    private final EducationActionRepository educationActionRepository;
    private final FacultyGroupRepository facultyGroupRepository;
    private final ImportantThingsRepository importantThingsRepository;
    private final JuryApplicationRepository juryApplicationRepository;
    private final JuryEvaluationRepository juryEvaluationRepository;
    private final MinMaxCriteriaRepository minMaxCriteriaRepository;
    private final PositionPostingRepository positionPostingRepository;
    private final PrerequisitesRepository prerequisitesRepository;
    private final PublicationCriteriaRepository publicationCriteriaRepository;
    private final ResearchProjectsRepository researchProjectsRepository;
    private final AdminJobOfferRepository adminJobOfferRepository;
    private final AppointmentRequirementsRepository appointmentRequirementsRepository;
    private final CategoryRepository categoryRepository;

    // GET
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @GetMapping("/faculties")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyRepository.findAll());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @GetMapping("/joboffers")
    public ResponseEntity<List<JobOffer>> getAllJobOffers() {
        return ResponseEntity.ok(jobOfferRepository.findAll());
    }

    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationRepository.findAll());
    }

    @GetMapping("/citations")
    public ResponseEntity<List<Citation>> getAllCitations() {
        return ResponseEntity.ok(citationRepository.findAll());
    }

    @GetMapping("/authorTypes")
    public ResponseEntity<List<AuthorType>> getAllAuthorTypes() {
        return ResponseEntity.ok(authorTypeRepository.findAll());
    }

    @GetMapping("/awards")
    public ResponseEntity<List<Award>> getAllAwards() {
        return ResponseEntity.ok(awardRepository.findAll());
    }

    @GetMapping("/articleTypes")
    public ResponseEntity<List<ArticleType>> getAllArticleTypes() {
        return ResponseEntity.ok(articleTypeRepository.findAll());
    }

    @GetMapping("/candidateDocuments")
    public ResponseEntity<List<CandidateDocument>> getAllCandidateDocuments() {
        return ResponseEntity.ok(candidateDocumentRepository.findAll());
    }

    @GetMapping("/contributionActivities")
    public ResponseEntity<List<ContributionActivities>> getAllContributionActivities() {
        return ResponseEntity.ok(contributionActivitiesRepository.findAll());
    }

    @GetMapping("/departmentManagerJobOffers")
    public ResponseEntity<List<DepartmentMenagerJobOffer>> getAllDepartmentManagerJobOffers() {
        return ResponseEntity.ok(departmentManagerJobRepository.findAll());
    }

    @GetMapping("/editorships")
    public ResponseEntity<List<Editorship>> getAllEditorships() {
        return ResponseEntity.ok(editorshipRepository.findAll());
    }

    @GetMapping("/educationActions")
    public ResponseEntity<List<EducationAction>> getAllEducationActions() {
        return ResponseEntity.ok(educationActionRepository.findAll());
    }

    @GetMapping("/facultyGroups")
    public ResponseEntity<List<FacultyGroup>> getAllFacultyGroups() {
        return ResponseEntity.ok(facultyGroupRepository.findAll());
    }

    @GetMapping("/importantThings")
    public ResponseEntity<List<ImportantThingsToFillTable>> getAllImportantThings() {
        return ResponseEntity.ok(importantThingsRepository.findAll());
    }

    @GetMapping("/juryApplications")
    public ResponseEntity<List<JuryApplication>> getAllJuryApplications() {
        return ResponseEntity.ok(juryApplicationRepository.findAll());
    }

    @GetMapping("/juryEvaluations")
    public ResponseEntity<List<JuryEvaluation>> getAllJuryEvaluations() {
        return ResponseEntity.ok(juryEvaluationRepository.findAll());
    }

    @GetMapping("/meetingActivities")
    public ResponseEntity<List<ScientificMeetingActivity>> getAllMeetingActivities() {
        return ResponseEntity.ok(meetingActivityRepository.findAll());
    }

    @GetMapping("/minMaxCriteria")
    public ResponseEntity<List<MinMaxPointCriteria>> getAllMinMaxCriteria() {
        return ResponseEntity.ok(minMaxCriteriaRepository.findAll());
    }

    @GetMapping("/positionPostings")
    public ResponseEntity<List<PositionPosting>> getAllPositionPostings() {
        return ResponseEntity.ok(positionPostingRepository.findAll());
    }

    @GetMapping("/prerequisites")
    public ResponseEntity<List<Prerequisites>> getAllPrerequisites() {
        return ResponseEntity.ok(prerequisitesRepository.findAll());
    }

    @GetMapping("/publicationCriteria")
    public ResponseEntity<List<PublicationCriteria>> getAllPublicationCriteria() {
        return ResponseEntity.ok(publicationCriteriaRepository.findAll());
    }

    @GetMapping("/researchProjects")
    public ResponseEntity<List<ResearchProjects>> getAllResearchProjects() {
        return ResponseEntity.ok(researchProjectsRepository.findAll());
    }

    @GetMapping("/adminJobOffers")
    public ResponseEntity<List<AdminJobOffer>> getAllAdminJobOffers() {
        return ResponseEntity.ok(adminJobOfferRepository.findAll());
    }

    @GetMapping("/appointmentRequirements")
    public ResponseEntity<List<AppointmentRequirements>> getAllAppointmentRequirements() {
        return ResponseEntity.ok(appointmentRequirementsRepository.findAll());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/supportingDocuments")
    public ResponseEntity<List<SupportingDocument>> getAllSupportingDocuments() {
        return ResponseEntity.ok(supportingDocumentRepository.findAll());
    }

    @GetMapping("/table3s")
    public ResponseEntity<List<Table3Action>> getAllTable3s() {
        return ResponseEntity.ok(table3Repository.findAll());
    }

    @GetMapping("/table4s")
    public ResponseEntity<List<Table4>> getAllTable4s() {
        return ResponseEntity.ok(table4Repository.findAll());
    }

    @GetMapping("/thesisSupervisions")
    public ResponseEntity<List<ThesisSupervision>> getAllThesisSupervisions() {
        return ResponseEntity.ok(thesisSupervisionRepository.findAll());
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleRepository.findAll());
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/patents")
    public ResponseEntity<List<Patent>> getAllPatents() {
        return ResponseEntity.ok(patentRepository.findAll());
    }

    // POST
    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @PostMapping("/article")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        return ResponseEntity.ok(articleRepository.save(article));
    }

    @PostMapping("/patent")
    public ResponseEntity<Patent> addPatent(@RequestBody Patent patent) {
        return ResponseEntity.ok(patentRepository.save(patent));
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/department")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentRepository.save(department));
    }

    @PostMapping("/faculty")
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyRepository.save(faculty));
    }

    @PostMapping("/role")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleRepository.save(role));
    }

    @PostMapping("/joboffer")
    public ResponseEntity<JobOffer> addJobOffer(@RequestBody JobOffer jobOffer) {
        return ResponseEntity.ok(jobOfferRepository.save(jobOffer));
    }

    @PostMapping("/application")
    public ResponseEntity<Application> addApplication(@RequestBody Application application) {
        return ResponseEntity.ok(applicationRepository.save(application));
    }

    @PostMapping("/citation")
    public ResponseEntity<Citation> addCitation(@RequestBody Citation citation) {
        return ResponseEntity.ok(citationRepository.save(citation));
    }

    @PostMapping("/authorType")
    public ResponseEntity<AuthorType> addAuthorType(@RequestBody AuthorType authorType) {
        return ResponseEntity.ok(authorTypeRepository.save(authorType));
    }

    @PostMapping("/award")
    public ResponseEntity<Award> addAward(@RequestBody Award award) {
        return ResponseEntity.ok(awardRepository.save(award));
    }

    @PostMapping("/articleType")
    public ResponseEntity<ArticleType> addArticleType(@RequestBody ArticleType articleType) {
        return ResponseEntity.ok(articleTypeRepository.save(articleType));
    }

    @PostMapping("/candidateDocument")
    public ResponseEntity<CandidateDocument> addCandidateDocument(@RequestBody CandidateDocument candidateDocument) {
        return ResponseEntity.ok(candidateDocumentRepository.save(candidateDocument));
    }

    @PostMapping("/contributionActivities")
    public ResponseEntity<ContributionActivities> addContributionActivities(
            @RequestBody ContributionActivities contributionActivities) {
        return ResponseEntity.ok(contributionActivitiesRepository.save(contributionActivities));
    }

    @PostMapping("/departmentManagerJobOffer")
    public ResponseEntity<DepartmentMenagerJobOffer> addDepartmentManagerJobOffer(
            @RequestBody DepartmentMenagerJobOffer departmentManagerJobOffer) {
        return ResponseEntity.ok(departmentManagerJobRepository.save(departmentManagerJobOffer));
    }

    @PostMapping("/editorship")
    public ResponseEntity<Editorship> addEditorship(@RequestBody Editorship editorship) {
        return ResponseEntity.ok(editorshipRepository.save(editorship));
    }

    @PostMapping("/educationAction")
    public ResponseEntity<EducationAction> addEducationAction(@RequestBody EducationAction educationAction) {
        return ResponseEntity.ok(educationActionRepository.save(educationAction));
    }

    @PostMapping("/facultyGroup")
    public ResponseEntity<FacultyGroup> addFacultyGroup(@RequestBody FacultyGroup facultyGroup) {
        return ResponseEntity.ok(facultyGroupRepository.save(facultyGroup));
    }

    @PostMapping("/importantThings")
    public ResponseEntity<ImportantThingsToFillTable> addImportantThings(
            @RequestBody ImportantThingsToFillTable importantThings) {
        return ResponseEntity.ok(importantThingsRepository.save(importantThings));
    }

    @PostMapping("/juryApplication")
    public ResponseEntity<JuryApplication> addJuryApplication(@RequestBody JuryApplication juryApplication) {
        return ResponseEntity.ok(juryApplicationRepository.save(juryApplication));
    }

    @PostMapping("/juryEvaluation")
    public ResponseEntity<JuryEvaluation> addJuryEvaluation(@RequestBody JuryEvaluation juryEvaluation) {
        return ResponseEntity.ok(juryEvaluationRepository.save(juryEvaluation));
    }

    @PostMapping("/meetingActivity")
    public ResponseEntity<ScientificMeetingActivity> addMeetingActivity(
            @RequestBody ScientificMeetingActivity meetingActivity) {
        return ResponseEntity.ok(meetingActivityRepository.save(meetingActivity));
    }

    @PostMapping("/minMaxCriteria")
    public ResponseEntity<MinMaxPointCriteria> addMinMaxCriteria(@RequestBody MinMaxPointCriteria minMaxPointCriteria) {
        return ResponseEntity.ok(minMaxCriteriaRepository.save(minMaxPointCriteria));
    }

    @PostMapping("/positionPosting")
    public ResponseEntity<PositionPosting> addPositionPosting(@RequestBody PositionPosting positionPosting) {
        return ResponseEntity.ok(positionPostingRepository.save(positionPosting));
    }

    @PostMapping("/prerequisites")
    public ResponseEntity<Prerequisites> addPrerequisites(@RequestBody Prerequisites prerequisites) {
        return ResponseEntity.ok(prerequisitesRepository.save(prerequisites));
    }

    @PostMapping("/publicationCriteria")
    public ResponseEntity<PublicationCriteria> addPublicationCriteria(
            @RequestBody PublicationCriteria publicationCriteria) {
        return ResponseEntity.ok(publicationCriteriaRepository.save(publicationCriteria));
    }

    @PostMapping("/researchProjects")
    public ResponseEntity<ResearchProjects> addResearchProjects(@RequestBody ResearchProjects researchProjects) {
        return ResponseEntity.ok(researchProjectsRepository.save(researchProjects));
    }

    @PostMapping("/adminJobOffer")
    public ResponseEntity<AdminJobOffer> addAdminJobOffer(@RequestBody AdminJobOffer adminJobOffer) {
        return ResponseEntity.ok(adminJobOfferRepository.save(adminJobOffer));
    }

    @PostMapping("/appointmentRequirements")
    public ResponseEntity<AppointmentRequirements> addAppointmentRequirements(
            @RequestBody AppointmentRequirements appointmentRequirements) {
        return ResponseEntity.ok(appointmentRequirementsRepository.save(appointmentRequirements));
    }

    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PostMapping("/supportingDocument")
    public ResponseEntity<SupportingDocument> addSupportingDocument(
            @RequestBody SupportingDocument supportingDocument) {
        return ResponseEntity.ok(supportingDocumentRepository.save(supportingDocument));
    }

    @PostMapping("/table3")
    public ResponseEntity<Table3Action> addTable3(@RequestBody Table3Action table3Action) {
        return ResponseEntity.ok(table3Repository.save(table3Action));
    }

    @PostMapping("/table4")
    public ResponseEntity<Table4> addTable4(@RequestBody Table4 table4) {
        return ResponseEntity.ok(table4Repository.save(table4));
    }

    @PostMapping("/thesisSupervision")
    public ResponseEntity<ThesisSupervision> addThesisSupervision(@RequestBody ThesisSupervision thesisSupervision) {
        return ResponseEntity.ok(thesisSupervisionRepository.save(thesisSupervision));
    }

    // örnek DELETE
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentRepository.deleteById(id);
        return ResponseEntity.ok("Department deleted successfully");
    }

    @DeleteMapping("/faculty/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long id) {
        facultyRepository.deleteById(id);
        return ResponseEntity.ok("Faculty deleted successfully");
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        roleRepository.deleteById(id);
        return ResponseEntity.ok("Role deleted successfully");
    }

    @DeleteMapping("/joboffer/{id}")
    public ResponseEntity<String> deleteJobOffer(@PathVariable Long id) {
        jobOfferRepository.deleteById(id);
        return ResponseEntity.ok("Job offer deleted successfully");
    }

    @DeleteMapping("/application/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {
        applicationRepository.deleteById(id);
        return ResponseEntity.ok("Application deleted successfully");
    }

    @DeleteMapping("/citation/{id}")
    public ResponseEntity<String> deleteCitation(@PathVariable Long id) {
        citationRepository.deleteById(id);
        return ResponseEntity.ok("Citation deleted successfully");
    }

    @DeleteMapping("/authorType/{id}")
    public ResponseEntity<String> deleteAuthorType(@PathVariable Long id) {
        authorTypeRepository.deleteById(id);
        return ResponseEntity.ok("Author type deleted successfully");
    }

    @DeleteMapping("/award/{id}")
    public ResponseEntity<String> deleteAward(@PathVariable Long id) {
        awardRepository.deleteById(id);
        return ResponseEntity.ok("Award deleted successfully");
    }

    @DeleteMapping("/articleType/{id}")
    public ResponseEntity<String> deleteArticleType(@PathVariable Long id) {
        articleTypeRepository.deleteById(id);
        return ResponseEntity.ok("Article type deleted successfully");
    }

    @DeleteMapping("/candidateDocument/{id}")
    public ResponseEntity<String> deleteCandidateDocument(@PathVariable Long id) {
        candidateDocumentRepository.deleteById(id);
        return ResponseEntity.ok("Candidate document deleted successfully");
    }

    @DeleteMapping("/contributionActivities/{id}")
    public ResponseEntity<String> deleteContributionActivities(@PathVariable Long id) {
        contributionActivitiesRepository.deleteById(id);
        return ResponseEntity.ok("Contribution activities deleted successfully");
    }

    @DeleteMapping("/departmentManagerJobOffer/{id}")
    public ResponseEntity<String> deleteDepartmentManagerJobOffer(@PathVariable Long id) {
        departmentManagerJobRepository.deleteById(id);
        return ResponseEntity.ok("Department manager job offer deleted successfully");
    }

    @DeleteMapping("/editorship/{id}")
    public ResponseEntity<String> deleteEditorship(@PathVariable Long id) {
        editorshipRepository.deleteById(id);
        return ResponseEntity.ok("Editorship deleted successfully");
    }

    @DeleteMapping("/educationAction/{id}")
    public ResponseEntity<String> deleteEducationAction(@PathVariable Long id) {
        educationActionRepository.deleteById(id);
        return ResponseEntity.ok("Education action deleted successfully");
    }

    @DeleteMapping("/facultyGroup/{id}")
    public ResponseEntity<String> deleteFacultyGroup(@PathVariable Long id) {
        facultyGroupRepository.deleteById(id);
        return ResponseEntity.ok("Faculty group deleted successfully");
    }

    @DeleteMapping("/importantThings/{id}")
    public ResponseEntity<String> deleteImportantThings(@PathVariable Long id) {
        importantThingsRepository.deleteById(id);
        return ResponseEntity.ok("Important things deleted successfully");
    }

    @DeleteMapping("/juryApplication/{id}")
    public ResponseEntity<String> deleteJuryApplication(@PathVariable Long id) {
        juryApplicationRepository.deleteById(id);
        return ResponseEntity.ok("Jury application deleted successfully");
    }

    @DeleteMapping("/juryEvaluation/{id}")
    public ResponseEntity<String> deleteJuryEvaluation(@PathVariable Long id) {
        juryEvaluationRepository.deleteById(id);
        return ResponseEntity.ok("Jury evaluation deleted successfully");
    }

    @DeleteMapping("/meetingActivity/{id}")
    public ResponseEntity<String> deleteMeetingActivity(@PathVariable Long id) {
        meetingActivityRepository.deleteById(id);
        return ResponseEntity.ok("Meeting activity deleted successfully");
    }

    @DeleteMapping("/minMaxCriteria/{id}")
    public ResponseEntity<String> deleteMinMaxCriteria(@PathVariable Long id) {
        minMaxCriteriaRepository.deleteById(id);
        return ResponseEntity.ok("Min max criteria deleted successfully");
    }

    @DeleteMapping("/positionPosting/{id}")
    public ResponseEntity<String> deletePositionPosting(@PathVariable Long id) {
        positionPostingRepository.deleteById(id);
        return ResponseEntity.ok("Position posting deleted successfully");
    }

    @DeleteMapping("/prerequisites/{id}")
    public ResponseEntity<String> deletePrerequisites(@PathVariable Long id) {
        prerequisitesRepository.deleteById(id);
        return ResponseEntity.ok("Prerequisites deleted successfully");
    }

    @DeleteMapping("/publicationCriteria/{id}")
    public ResponseEntity<String> deletePublicationCriteria(@PathVariable Long id) {
        publicationCriteriaRepository.deleteById(id);
        return ResponseEntity.ok("Publication criteria deleted successfully");
    }

    @DeleteMapping("/researchProjects/{id}")
    public ResponseEntity<String> deleteResearchProjects(@PathVariable Long id) {
        researchProjectsRepository.deleteById(id);
        return ResponseEntity.ok("Research projects deleted successfully");
    }

    @DeleteMapping("/adminJobOffer/{id}")
    public ResponseEntity<String> deleteAdminJobOffer(@PathVariable Long id) {
        adminJobOfferRepository.deleteById(id);
        return ResponseEntity.ok("Admin job offer deleted successfully");
    }

    @DeleteMapping("/appointmentRequirements/{id}")
    public ResponseEntity<String> deleteAppointmentRequirements(@PathVariable Long id) {
        appointmentRequirementsRepository.deleteById(id);
        return ResponseEntity.ok("Appointment requirements deleted successfully");
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @DeleteMapping("/supportingDocument/{id}")
    public ResponseEntity<String> deleteSupportingDocument(@PathVariable Long id) {
        supportingDocumentRepository.deleteById(id);
        return ResponseEntity.ok("Supporting document deleted successfully");
    }

    @DeleteMapping("/table3/{id}")
    public ResponseEntity<String> deleteTable3(@PathVariable Long id) {
        table3Repository.deleteById(id);
        return ResponseEntity.ok("Table 3 deleted successfully");
    }

    @DeleteMapping("/table4/{id}")
    public ResponseEntity<String> deleteTable4(@PathVariable Long id) {
        table4Repository.deleteById(id);
        return ResponseEntity.ok("Table 4 deleted successfully");
    }

    @DeleteMapping("/thesisSupervision/{id}")
    public ResponseEntity<String> deleteThesisSupervision(@PathVariable Long id) {
        thesisSupervisionRepository.deleteById(id);
        return ResponseEntity.ok("Thesis supervision deleted successfully");
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return ResponseEntity.ok("Article deleted successfully");
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @DeleteMapping("/patent/{id}")
    public ResponseEntity<String> deletePatent(@PathVariable Long id) {
        patentRepository.deleteById(id);
        return ResponseEntity.ok("Patent deleted successfully");
    }

    // PUT
    /*
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setUserRole(updatedUser.getUserRole());
                    return ResponseEntity.ok(userRepository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/faculties/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty updatedFaculty) {
        return facultyRepository.findById(id)
                .map(faculty -> {
                    faculty.setFacultyName(updatedFaculty.getFacultyName());
                    return ResponseEntity.ok(facultyRepository.save(faculty));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setDepartmentName(updatedDepartment.getDepartmentName());
                    return ResponseEntity.ok(departmentRepository.save(department));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role updatedRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setRoleName(updatedRole.getRoleName());
                    return ResponseEntity.ok(roleRepository.save(role));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/joboffers/{id}")
    public ResponseEntity<JobOffer> updateJobOffer(@PathVariable Long id, @RequestBody JobOffer updatedJobOffer) {
        return jobOfferRepository.findById(id)
                .map(jobOffer -> {
                    jobOffer.setTitle(updatedJobOffer.getTitle());
                    jobOffer.setDescription(updatedJobOffer.getDescription());
                    return ResponseEntity.ok(jobOfferRepository.save(jobOffer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/applications/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long id, @RequestBody Application updatedApplication) {
        return applicationRepository.findById(id)
                .map(application -> {
                    application.setStatus(updatedApplication.getStatus());
                    return ResponseEntity.ok(applicationRepository.save(application));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/citations/{id}")
    public ResponseEntity<Citation> updateCitation(@PathVariable Long id, @RequestBody Citation updatedCitation) {
        return citationRepository.findById(id)
                .map(citation -> {
                    citation.setTitle(updatedCitation.getTitle());
                    citation.setAuthor(updatedCitation.getAuthor());
                    return ResponseEntity.ok(citationRepository.save(citation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/authorTypes/{id}")
    public ResponseEntity<AuthorType> updateAuthorType(@PathVariable Long id, @RequestBody AuthorType updatedAuthorType) {
        return authorTypeRepository.findById(id)
                .map(authorType -> {
                    authorType.setName(updatedAuthorType.getName());
                    return ResponseEntity.ok(authorTypeRepository.save(authorType));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/awards/{id}")
    public ResponseEntity<Award> updateAward(@PathVariable Long id, @RequestBody Award updatedAward) {
        return awardRepository.findById(id)
                .map(award -> {
                    award.setName(updatedAward.getName());
                    return ResponseEntity.ok(awardRepository.save(award));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/articleTypes/{id}")
    public ResponseEntity<ArticleType> updateArticleType(@PathVariable Long id, @RequestBody ArticleType updatedArticleType) {
        return articleTypeRepository.findById(id)
                .map(articleType -> {
                    articleType.setName(updatedArticleType.getName());
                    return ResponseEntity.ok(articleTypeRepository.save(articleType));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/candidateDocuments/{id}")
    public ResponseEntity<CandidateDocument> updateCandidateDocument(@PathVariable Long id, @RequestBody CandidateDocument updatedCandidateDocument) {
        return candidateDocumentRepository.findById(id)
                .map(candidateDocument -> {
                    candidateDocument.setName(updatedCandidateDocument.getName());
                    return ResponseEntity.ok(candidateDocumentRepository.save(candidateDocument));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/contributionActivities/{id}")
    public ResponseEntity<ContributionActivities> updateContributionActivities(@PathVariable Long id, @RequestBody ContributionActivities updatedContributionActivities) {
        return contributionActivitiesRepository.findById(id)
                .map(contributionActivities -> {
                    contributionActivities.setName(updatedContributionActivities.getName());
                    return ResponseEntity.ok(contributionActivitiesRepository.save(contributionActivities));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/departmentManagerJobOffers/{id}")
    public ResponseEntity<DepartmentMenagerJobOffer> updateDepartmentManagerJobOffer(@PathVariable Long id, @RequestBody DepartmentMenagerJobOffer updatedDepartmentManagerJobOffer) {
        return departmentManagerJobRepository.findById(id)
                .map(departmentManagerJobOffer -> {
                    departmentManagerJobOffer.setTitle(updatedDepartmentManagerJobOffer.getTitle());
                    return ResponseEntity.ok(departmentManagerJobRepository.save(departmentManagerJobOffer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/editorships/{id}")
    public ResponseEntity<Editorship> updateEditorship(@PathVariable Long id, @RequestBody Editorship updatedEditorship) {
        return editorshipRepository.findById(id)
                .map(editorship -> {
                    editorship.setName(updatedEditorship.getName());
                    return ResponseEntity.ok(editorshipRepository.save(editorship));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/educationActions/{id}")
    public ResponseEntity<EducationAction> updateEducationAction(@PathVariable Long id, @RequestBody EducationAction updatedEducationAction) {
        return educationActionRepository.findById(id)
                .map(educationAction -> {
                    educationAction.setName(updatedEducationAction.getName());
                    return ResponseEntity.ok(educationActionRepository.save(educationAction));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/facultyGroups/{id}")
    public ResponseEntity<FacultyGroup> updateFacultyGroup(@PathVariable Long id, @RequestBody FacultyGroup updatedFacultyGroup) {
        return facultyGroupRepository.findById(id)
                .map(facultyGroup -> {
                    facultyGroup.setName(updatedFacultyGroup.getName());
                    return ResponseEntity.ok(facultyGroupRepository.save(facultyGroup));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/importantThings/{id}")
    public ResponseEntity<ImportantThingsToFillTable> updateImportantThings(@PathVariable Long id, @RequestBody ImportantThingsToFillTable updatedImportantThings) {
        return importantThingsRepository.findById(id)
                .map(importantThings -> {
                    importantThings.setName(updatedImportantThings.getName());
                    return ResponseEntity.ok(importantThingsRepository.save(importantThings));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/juryApplications/{id}")
    public ResponseEntity<JuryApplication> updateJuryApplication(@PathVariable Long id, @RequestBody JuryApplication updatedJuryApplication) {
        return juryApplicationRepository.findById(id)
                .map(juryApplication -> {
                    juryApplication.setStatus(updatedJuryApplication.getStatus());
                    return ResponseEntity.ok(juryApplicationRepository.save(juryApplication));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/juryEvaluations/{id}")
    public ResponseEntity<JuryEvaluation> updateJuryEvaluation(@PathVariable Long id, @RequestBody JuryEvaluation updatedJuryEvaluation) {
        return juryEvaluationRepository.findById(id)
                .map(juryEvaluation -> {
                    juryEvaluation.setScore(updatedJuryEvaluation.getScore());
                    return ResponseEntity.ok(juryEvaluationRepository.save(juryEvaluation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/meetingActivities/{id}")
    public ResponseEntity<ScientificMeetingActivity> updateMeetingActivity(@PathVariable Long id, @RequestBody ScientificMeetingActivity updatedMeetingActivity) {
        return meetingActivityRepository.findById(id)
                .map(meetingActivity -> {
                    meetingActivity.setName(updatedMeetingActivity.getName());
                    return ResponseEntity.ok(meetingActivityRepository.save(meetingActivity));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/minMaxCriteria/{id}")
    public ResponseEntity<MinMaxPointCriteria> updateMinMaxCriteria(@PathVariable Long id, @RequestBody MinMaxPointCriteria updatedMinMaxPointCriteria) {
        return minMaxCriteriaRepository.findById(id)
                .map(minMaxPointCriteria -> {
                    minMaxPointCriteria.setName(updatedMinMaxPointCriteria.getName());
                    return ResponseEntity.ok(minMaxCriteriaRepository.save(minMaxPointCriteria));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/positionPostings/{id}")
    public ResponseEntity<PositionPosting> updatePositionPosting(@PathVariable Long id, @RequestBody PositionPosting updatedPositionPosting) {
        return positionPostingRepository.findById(id)
                .map(positionPosting -> {
                    positionPosting.setName(updatedPositionPosting.getName());
                    return ResponseEntity.ok(positionPostingRepository.save(positionPosting));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/prerequisites/{id}")
    public ResponseEntity<Prerequisites> updatePrerequisites(@PathVariable Long id, @RequestBody Prerequisites updatedPrerequisites) {
        return prerequisitesRepository.findById(id)
                .map(prerequisites -> {
                    prerequisites.setName(updatedPrerequisites.getName());
                    return ResponseEntity.ok(prerequisitesRepository.save(prerequisites));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/publicationCriteria/{id}")
    public ResponseEntity<PublicationCriteria> updatePublicationCriteria(@PathVariable Long id, @RequestBody PublicationCriteria updatedPublicationCriteria) {
        return publicationCriteriaRepository.findById(id)
                .map(publicationCriteria -> {
                    publicationCriteria.setName(updatedPublicationCriteria.getName());
                    return ResponseEntity.ok(publicationCriteriaRepository.save(publicationCriteria));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/researchProjects/{id}")
    public ResponseEntity<ResearchProjects> updateResearchProjects(@PathVariable Long id, @RequestBody ResearchProjects updatedResearchProjects) {
        return researchProjectsRepository.findById(id)
                .map(researchProjects -> {
                    researchProjects.setName(updatedResearchProjects.getName());
                    return ResponseEntity.ok(researchProjectsRepository.save(researchProjects));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/adminJobOffers/{id}")
    public ResponseEntity<AdminJobOffer> updateAdminJobOffer(@PathVariable Long id, @RequestBody AdminJobOffer updatedAdminJobOffer) {
        return adminJobOfferRepository.findById(id)
                .map(adminJobOffer -> {
                    adminJobOffer.setTitle(updatedAdminJobOffer.getTitle());
                    return ResponseEntity.ok(adminJobOfferRepository.save(adminJobOffer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/appointmentRequirements/{id}")
    public ResponseEntity<AppointmentRequirements> updateAppointmentRequirements(@PathVariable Long id, @RequestBody AppointmentRequirements updatedAppointmentRequirements) {
        return appointmentRequirementsRepository.findById(id)
                .map(appointmentRequirements -> {
                    appointmentRequirements.setName(updatedAppointmentRequirements.getName());
                    return ResponseEntity.ok(appointmentRequirementsRepository.save(appointmentRequirements));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    return ResponseEntity.ok(categoryRepository.save(category));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/supportingDocuments/{id}")
    public ResponseEntity<SupportingDocument> updateSupportingDocument(@PathVariable Long id, @RequestBody SupportingDocument updatedSupportingDocument) {
        return supportingDocumentRepository.findById(id)
                .map(supportingDocument -> {
                    supportingDocument.setName(updatedSupportingDocument.getName());
                    return ResponseEntity.ok(supportingDocumentRepository.save(supportingDocument));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/table3Actions/{id}")
    public ResponseEntity<Table3Action> updateTable3(@PathVariable Long id, @RequestBody Table3Action updatedTable3Action) {
        return table3Repository.findById(id)
                .map(table3Action -> {
                    table3Action.setName(updatedTable3Action.getName());
                    return ResponseEntity.ok(table3Repository.save(table3Action));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/table4/{id}")
    public ResponseEntity<Table4> updateTable4(@PathVariable Long id, @RequestBody Table4 updatedTable4) {
        return table4Repository.findById(id)
                .map(table4 -> {
                    table4.setName(updatedTable4.getName());
                    return ResponseEntity.ok(table4Repository.save(table4));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/thesisSupervisions/{id}")
    public ResponseEntity<ThesisSupervision> updateThesisSupervision(@PathVariable Long id, @RequestBody ThesisSupervision updatedThesisSupervision) {
        return thesisSupervisionRepository.findById(id)
                .map(thesisSupervision -> {
                    thesisSupervision.setName(updatedThesisSupervision.getName());
                    return ResponseEntity.ok(thesisSupervisionRepository.save(thesisSupervision));
                })
                .orElse(ResponseEntity.notFound().build());

    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        return articleRepository.findById(id)
                .map(article -> {
                    article.setTitle(updatedArticle.getTitle());
                    return ResponseEntity.ok(articleRepository.save(article));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    return ResponseEntity.ok(bookRepository.save(book));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/patents/{id}")
    public ResponseEntity<Patent> updatePatent(@PathVariable Long id, @RequestBody Patent updatedPatent) {
        return patentRepository.findById(id)
                .map(patent -> {
                    patent.setTitle(updatedPatent.getTitle());
                    return ResponseEntity.ok(patentRepository.save(patent));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    */
}
