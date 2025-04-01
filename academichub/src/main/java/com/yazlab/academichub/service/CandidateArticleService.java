package com.yazlab.academichub.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.ArticleType;
import com.yazlab.academichub.entities.AuthorType;
import com.yazlab.academichub.entities.candidateDocuments.CandidateArticle;
import com.yazlab.academichub.entities.candidateDocuments.CandidateAuthor;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.ArticleTypeRepository;
import com.yazlab.academichub.repository.AuthorTypeRepository;
import com.yazlab.academichub.repository.CandidateArticleRepository;
import com.yazlab.academichub.request.AddAuthorRequest;
import com.yazlab.academichub.request.CreateArticleRequest;
import com.yazlab.academichub.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateArticleService {

    private final ApplicationRepository applicationRepository;

    private final CandidateArticleRepository candidateArticleRepository;

    private final ArticleTypeRepository articleTypeRepository;

    private final AuthorTypeRepository authorTypeRepository;

    @Transactional
    public ApiResponse addArticle(Long applictionId, CreateArticleRequest request) throws ApplicationException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ApplicationException("Application could not find by Id -> " + applictionId.toString());
        }

        CandidateArticle existCandidateArticle = candidateArticleRepository.findByArticleName(request.getArticleName());

        if (existCandidateArticle != null) {

            ApiResponse apiResponse = new ApiResponse();

            apiResponse.setMessage("The Article loaded already!");

            return apiResponse;
        }

        CandidateArticle candidateArticle = new CandidateArticle();

        candidateArticle.setArticleName(request.getArticleName());

        ArticleType articleType = articleTypeRepository.findByArticleTypeName(request.getArticleTypeName());

        if (articleType == null) {
            throw new ApplicationException("Article type could not find by Id -> " + request.getArticleTypeName());
        }

        candidateArticle.setArticleType(articleType);

        candidateArticle.setArticleCategory(request.getArticleCategory());

        candidateArticle.setAuthorCount(request.getAuthorCount());

        Set<CandidateAuthor> candidateAuthors = convert(request.getAuthors());

        candidateArticle.setAuthors(candidateAuthors);

        candidateArticle.setApplication(application);

        candidateArticle.setPhotoLink(request.getPhotoLink());

        candidateArticleRepository.save(candidateArticle);

        application.addArticle(candidateArticle);

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Article has been loaded successfully!");

        //change the application!!! add the new article to set of articles of application



        return apiResponse;
    }
    
    public Set<CandidateAuthor> convert(Set<AddAuthorRequest> addAuthorRequests) {

        return addAuthorRequests.stream().map(this::convertSingleAuthor).collect(Collectors.toSet());
    }

    public CandidateAuthor convertSingleAuthor(AddAuthorRequest request){

        CandidateAuthor candidateAuthor = new CandidateAuthor();

        candidateAuthor.setName(request.getName());

        candidateAuthor.setSurname(request.getSurname());

        AuthorType authorType = authorTypeRepository.findByAuthorTypeName(request.getAuthorTypeName());

        candidateAuthor.setAuthorType(authorType);

        return candidateAuthor;
    }

}
