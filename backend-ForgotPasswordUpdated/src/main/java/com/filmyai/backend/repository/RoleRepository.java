package com.filmyai.backend.repository;

import com.filmyai.backend.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    List<Roles> findByProjectProjectId(Long projectId);
}
