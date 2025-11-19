package com.filmyai.backend.repository;


import com.filmyai.backend.model.Currencies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currencies, Long> {
}
