package com.filmyai.backend.service.Ethnicity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filmyai.backend.dto.Ethnicity.EthnicityResponseDto;
import com.filmyai.backend.model.Ethnicity;
import com.filmyai.backend.repository.EthnicityRepository;

@Service
public class EthnicityService {

    private final EthnicityRepository ethnicityRepository;

    public EthnicityService(EthnicityRepository ethnicityRepository) {
        this.ethnicityRepository = ethnicityRepository;
    }

    public List<EthnicityResponseDto> getAllEthnicities() {
        List<Ethnicity> ethnicities = ethnicityRepository.findAll();
        List<EthnicityResponseDto> responseList = new ArrayList<>();

        for (Ethnicity ethnicity : ethnicities) {
            responseList.add(new EthnicityResponseDto(ethnicity.getEthnicityId(),ethnicity.getEthnicityName()));
        }

        return responseList;
    }
    
    public EthnicityResponseDto getEthnicitiesById(Long id) {
    	Ethnicity ethnicity = ethnicityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ethnicity with id: " + id + " not found"));

        return new EthnicityResponseDto(ethnicity.getEthnicityId(),ethnicity.getEthnicityName());
    }
    

}