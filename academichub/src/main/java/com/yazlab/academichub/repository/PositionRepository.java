package com.yazlab.academichub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yazlab.academichub.entities.Position;

public interface PositionRepository extends JpaRepository<Position,Long> {

    Position findByPositionName(String positionName);

}
