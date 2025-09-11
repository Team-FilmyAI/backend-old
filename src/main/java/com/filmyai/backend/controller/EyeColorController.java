package com.filmyai.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.backend.dto.EyeColor.EyeColorResponseDto;
import com.filmyai.backend.service.EyeColor.EyeColorService;

@RestController
@RequestMapping("/filmyai")
public class EyeColorController {

    private final EyeColorService eyeColorService;

    public EyeColorController(EyeColorService eyeColorService) {
        this.eyeColorService = eyeColorService;
    }

    @GetMapping("/eyeColors")
    public ResponseEntity<List<EyeColorResponseDto>> getAllEyeColors() {
        return ResponseEntity.ok(eyeColorService.getAllEyeColors());
    }
    
    @GetMapping("/eyeColors/{eyeColorId}")
    public ResponseEntity<EyeColorResponseDto> getEyeColorById(@PathVariable Long eyeColorId) {
    	EyeColorResponseDto response = eyeColorService.getEyeColorById(eyeColorId);
        return ResponseEntity.ok(response);
    }

}