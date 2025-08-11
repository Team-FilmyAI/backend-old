package com.filmyai.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.backend.dto.Ethnicity.EthnicityResponseDto;
import com.filmyai.backend.service.Ethnicity.EthnicityService;

@RestController
@RequestMapping("/filmyai")
public class EthnicityController {

    private final EthnicityService ethnicityService;

    public EthnicityController(EthnicityService ethnicityService) {
        this.ethnicityService = ethnicityService;
    }

    @GetMapping("/ethnicities")
    public ResponseEntity<List<EthnicityResponseDto>> getAllEthnicities() {
        return ResponseEntity.ok(ethnicityService.getAllEthnicities());
    }
    
    @GetMapping("/ethnicities/{ethnicityId}")
    public ResponseEntity<EthnicityResponseDto> getEthnicitiesById(@PathVariable Long ethnicityId) {
    	EthnicityResponseDto response = ethnicityService.getEthnicitiesById(ethnicityId);
        return ResponseEntity.ok(response);
    }

}