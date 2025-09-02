package com.filmyai.backend.repository;

import com.filmyai.backend.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}