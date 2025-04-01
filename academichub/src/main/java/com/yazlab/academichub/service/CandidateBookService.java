package com.yazlab.academichub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.Application;
import com.yazlab.academichub.entities.candidateDocuments.CandidateBook;
import com.yazlab.academichub.exception.ApplicationException;
import com.yazlab.academichub.exception.CandidateBookException;
import com.yazlab.academichub.repository.ApplicationRepository;
import com.yazlab.academichub.repository.CandidateBookRepository;
import com.yazlab.academichub.response.ApiResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateBookService {

    private final ApplicationRepository applicationRepository;

    private final CandidateBookRepository candidateBookRepository;

    @Transactional
    public ApiResponse addBook(Long applictionId, CandidateBook request) throws CandidateBookException {

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new CandidateBookException("Application could not find by Id -> " + applictionId.toString());
        }

        List<CandidateBook> existCandidateBooks = candidateBookRepository.findByBookName(request.getBookName());

        boolean bookExist = existCandidateBooks.stream()
                .anyMatch(book -> book.getApplication() != null
                        && book.getApplication().getApplicationId().equals(applictionId));

        if (bookExist) {

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("The Book loaded already!");
            return apiResponse;
        }

        CandidateBook candidateBook = new CandidateBook();

        candidateBook.setBookCategory(request.getBookCategory());

        candidateBook.setBookName(request.getBookName());

        candidateBook.setPublisher(request.getPublisher());

        candidateBook.setEditionNumber(request.getEditionNumber());

        candidateBook.setPublicationVenue(request.getPublicationVenue());

        candidateBook.setYear(request.getYear());

        candidateBook.setForeignLanguage(request.isForeignLanguage());

        candidateBook.setPhotoLink(request.getPhotoLink());

        candidateBook.setApplication(application);

        candidateBookRepository.save(candidateBook);

        application.addBook(candidateBook);

        applicationRepository.save(application);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("Scientific Meeting Activity has been loaded successfully!");

        return apiResponse;
    }

    public ApiResponse deleteBook(Long applictionId, Long bookId ) throws ApplicationException{

        Application application = applicationRepository.findByApplicationId(applictionId);

        if (application == null) {
            throw new ApplicationException("Application could not find by Id -> " + applictionId.toString());
        }

        CandidateBook candidateBook = candidateBookRepository.findByBookNameAndApplicationId(bookId, applictionId);

        if (candidateBook==null) {
            throw new ApplicationException("SMA could not find by Id -> " + bookId.toString());
        }

        candidateBookRepository.delete(candidateBook);

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("SMA delete successfully!");

        return apiResponse;
        
    }
}
