package com.filmyai.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.backend.dto.HairColor.HairColorResponseDto;
import com.filmyai.backend.service.HairColor.HairColorService;

@RestController
@RequestMapping("/filmyai")
public class HairColorController {

    private final HairColorService hairColorService;

    public HairColorController(HairColorService hairColorService) {
        this.hairColorService = hairColorService;
    }

    @GetMapping("/hairColors")
    public ResponseEntity<List<HairColorResponseDto>> getAllHairColors() {
        return ResponseEntity.ok(hairColorService.getAllHairColors());
    }
    
    @GetMapping("/hairColors/{hairColorId}")
    public ResponseEntity<HairColorResponseDto> getHairColorById(@PathVariable Long hairColorId) {
    	HairColorResponseDto response = hairColorService.getHairColorById(hairColorId);
        return ResponseEntity.ok(response);
    }

}