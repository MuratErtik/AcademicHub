package com.yazlab.academichub.repository;

import com.yazlab.academichub.entities.Editorship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorshipRepository extends JpaRepository<Editorship, Long> {
}
