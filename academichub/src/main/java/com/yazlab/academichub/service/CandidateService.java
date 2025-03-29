package com.yazlab.academichub.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.JobOffer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final JobOfferService jobOfferService;

    public List<JobOffer> listAllOffers() {

        LocalDateTime now = LocalDateTime.now();

        List<JobOffer> allJobOffers = jobOfferService.getAllJobOffer();

        List<JobOffer> jobOffersToList = allJobOffers.stream()
                .filter(offer -> offer.getTitle() != null)
                .filter(offer -> offer.getDescription() != null)
                .filter(offer -> offer.getDepartment()!=null)
                .filter(offer -> offer.getPosition()!=null)
                .filter(offer -> offer.getStartDate() != null && offer.getEndDate() != null)
                .filter(offer -> now.isAfter(offer.getStartDate()) && now.isBefore(offer.getEndDate()))
                .filter(offer -> !offer.getMinMaxPointCriterias().isEmpty() && !offer.getPublicationCriterias().isEmpty())
                .collect(Collectors.toList());

        return jobOffersToList;
    }

}
