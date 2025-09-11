package com.filmyai.backend.service.EyeColor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filmyai.backend.dto.EyeColor.EyeColorResponseDto;
import com.filmyai.backend.model.EyeColor;
import com.filmyai.backend.repository.EyeColorRepository;

@Service
public class EyeColorService {

    private final EyeColorRepository eyeColorRepository; 

    public EyeColorService(EyeColorRepository eyeColorRepository) {
        this.eyeColorRepository = eyeColorRepository;
    }

    public List<EyeColorResponseDto> getAllEyeColors() {
        List<EyeColor> eyeColors = eyeColorRepository.findAll();
        List<EyeColorResponseDto> responseList = new ArrayList<>();

        for (EyeColor eyecolor : eyeColors) {
            responseList.add(new EyeColorResponseDto(eyecolor.getEyeColorId(),eyecolor.getEyeColorName()));
        }

        return responseList;
    }
    
    public EyeColorResponseDto getEyeColorById(Long id) {
    	EyeColor eyecolor = eyeColorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("EyeColor with id: " + id + " not found"));

        return new EyeColorResponseDto(eyecolor.getEyeColorId(),eyecolor.getEyeColorName());
    }
    

}