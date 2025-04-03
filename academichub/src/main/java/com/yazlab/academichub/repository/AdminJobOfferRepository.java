package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminJobOfferRepository extends JpaRepository<AdminJobOffer, Long> {}