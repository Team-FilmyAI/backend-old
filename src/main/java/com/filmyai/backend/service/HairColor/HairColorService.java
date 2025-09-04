package com.filmyai.backend.service.HairColor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filmyai.backend.dto.HairColor.HairColorResponseDto;
import com.filmyai.backend.model.HairColor;
import com.filmyai.backend.repository.HairColorRepository;

@Service
public class HairColorService {

    private final HairColorRepository hairColorRepository;

    public HairColorService(HairColorRepository hairColorRepository) {
        this.hairColorRepository = hairColorRepository;
    }

    public List<HairColorResponseDto> getAllHairColors() {
        List<HairColor> hairColors = hairColorRepository.findAll();
        List<HairColorResponseDto> responseList = new ArrayList<>();

        for (HairColor hairColor : hairColors) {
            responseList.add(new HairColorResponseDto(hairColor.getHairColorId(),hairColor.getHairColorName()));
        }

        return responseList;
    }
    
    public HairColorResponseDto getHairColorById(Long id) {
    	HairColor hairColor = hairColorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("HairColor with id: " + id + " not found"));

        return new HairColorResponseDto(hairColor.getHairColorId(),hairColor.getHairColorName());
    }
    

}