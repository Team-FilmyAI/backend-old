package com.filmyai.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmyai.backend.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
