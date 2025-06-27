package com.filmyai.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmyai.backend.model.State;

public interface StateRepository extends JpaRepository<State, Long>  {
	
	
}
