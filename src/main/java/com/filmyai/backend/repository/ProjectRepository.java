package com.filmyai.backend.repository;

import com.filmyai.backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // 1. All projects with jobs + genres + producers
    @Query("SELECT DISTINCT p FROM Project p " +
            "LEFT JOIN FETCH p.jobs j " +
            "LEFT JOIN FETCH j.role " +
            "LEFT JOIN FETCH j.jobActor " +
            "LEFT JOIN FETCH p.genres g " +
            "LEFT JOIN FETCH p.producers pr")
    List<Project> findAllWithJobs();

    // 2. One project by ID with jobs + genres + producers
    @Query("SELECT DISTINCT p FROM Project p " +
            "LEFT JOIN FETCH p.jobs j " +
            "LEFT JOIN FETCH j.role " +
            "LEFT JOIN FETCH j.jobActor " +
            "LEFT JOIN FETCH p.genres g " +
            "LEFT JOIN FETCH p.producers pr " +
            "WHERE p.projectId = :id")
    Optional<Project> findByIdWithJobs(@Param("id") Long id);

    // 3. Projects filtered by genre with jobs + genres
    @Query("SELECT DISTINCT p FROM Project p " +
            "JOIN p.genres g " +
            "LEFT JOIN FETCH p.jobs j " +
            "LEFT JOIN FETCH j.role " +
            "LEFT JOIN FETCH j.jobActor " +
            "LEFT JOIN FETCH p.genres " +
            "WHERE g.genreId = :genreId")
    List<Project> findByGenreWithJobs(@Param("genreId") Long genreId);

    // 4. Active projects filtered by producer
    @Query("SELECT DISTINCT p FROM Project p " +
            "JOIN p.producers pr " +
            "LEFT JOIN FETCH p.jobs j " +
            "LEFT JOIN FETCH j.role " +
            "LEFT JOIN FETCH j.jobActor " +
            "LEFT JOIN FETCH p.genres " +
            "WHERE pr.userId = :userId AND p.status = 'Active'")
    List<Project> findActiveProjectsByProducer(@Param("userId") Long userId);
}
