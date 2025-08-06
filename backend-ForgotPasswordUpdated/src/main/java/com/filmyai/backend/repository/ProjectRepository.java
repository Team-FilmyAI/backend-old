package com.filmyai.backend.repository;

import com.filmyai.backend.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Projects, Long> {
    @Query("SELECT p FROM Projects p LEFT JOIN FETCH p.roles r LEFT JOIN FETCH r.gender LEFT JOIN FETCH r.currency LEFT JOIN FETCH p.category")
    List<Projects> findAllWithRoles();
}
