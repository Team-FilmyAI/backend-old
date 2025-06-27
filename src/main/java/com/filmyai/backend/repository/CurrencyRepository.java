package com.filmyai.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmyai.backend.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	
	
}
