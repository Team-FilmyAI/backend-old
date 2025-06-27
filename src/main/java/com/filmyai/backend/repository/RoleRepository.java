package com.filmyai.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmyai.backend.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
