package com.filmyai.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.backend.dto.City.CityResponseDto;
import com.filmyai.backend.service.City.CityService;

@RestController
@RequestMapping("/filmyai")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityResponseDto>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }
    
    @GetMapping("/cities/{cityId}")
    public ResponseEntity<CityResponseDto> getCityById(@PathVariable Long cityId) {
    	CityResponseDto response = cityService.getCityById(cityId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/states/{stateId}/cities")
    public ResponseEntity<List<CityResponseDto>> getCitiesByStateId(@PathVariable Long stateId) {
        return ResponseEntity.ok(cityService.getCitiesByStateId(stateId));
    }
}