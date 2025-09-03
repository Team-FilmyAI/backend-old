package com.filmyai.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.filmyai.backend.dto.Country.CountryResponseDto;

import com.filmyai.backend.service.Country.CountryService;

@RestController
@RequestMapping("/filmyai")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryResponseDto>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }
    
    @GetMapping("/countries/{id}")
    public ResponseEntity<CountryResponseDto> getCountryById(@PathVariable Long id) {
        CountryResponseDto response = countryService.getCountryById(id);
        return ResponseEntity.ok(response);
    }
}