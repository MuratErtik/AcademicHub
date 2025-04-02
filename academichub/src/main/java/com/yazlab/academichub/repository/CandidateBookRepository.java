package com.yazlab.academichub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yazlab.academichub.entities.candidateDocuments.CandidateBook;

public interface CandidateBookRepository extends JpaRepository<CandidateBook,Long> {

    List<CandidateBook> findByBookName(String bookName);

    @Query(value = "select * from candidate_book where candidate_book_id = :candidateBookId and application_id = :applicationId", nativeQuery = true)
    CandidateBook findByBookNameAndApplicationId(@Param("candidateBookId") Long candidateBookId, @Param("applicationId") Long applicationId);
}
