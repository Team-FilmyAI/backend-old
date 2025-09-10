package com.filmyai.backend.repository;

import com.filmyai.backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.jobs j LEFT JOIN FETCH j.role LEFT JOIN FETCH j.jobActor LEFT JOIN FETCH p.genre")
    List<Project> findAllWithJobs();

    @Query("SELECT p FROM Project p " +
            "LEFT JOIN FETCH p.jobs j " +
            "LEFT JOIN FETCH j.role " +
            "LEFT JOIN FETCH j.jobActor " +
            "LEFT JOIN FETCH p.genre " +
            "WHERE p.projectId = :id")
    Optional<Project> findByIdWithJobs(@Param("id") Long id);

    @Query("SELECT DISTINCT p FROM Project p " +
            "LEFT JOIN FETCH p.jobs j " +
            "LEFT JOIN FETCH j.role " +
            "LEFT JOIN FETCH j.jobActor " +
            "LEFT JOIN FETCH p.genre g " +
            "WHERE g.genreId = :genreId")
    List<Project> findByGenreIdWithJobs(@Param("genreId") Long genreId);
}
