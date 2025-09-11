package com.filmyai.backend.service.City;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filmyai.backend.dto.City.CityResponseDto;
import com.filmyai.backend.model.City;
import com.filmyai.backend.repository.CityRepository;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityResponseDto> getAllCities() {
        List<City> cities = cityRepository.findAll();
        List<CityResponseDto> responseList = new ArrayList<>();

        for (City city : cities) {
            responseList.add(new CityResponseDto(city.getCityId(),city.getCityName()));
        }

        return responseList;
    }
    
    public CityResponseDto getCityById(Long id) {
    	City city = cityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("City with id: " + id + " not found"));

        return new CityResponseDto(city.getCityId(),city.getCityName());
    }
    
	public List<CityResponseDto> getCitiesByStateId(Long stateId) {
		List<City> cities = cityRepository.findByState_StateId(stateId);
		List<CityResponseDto> responseList = new ArrayList<>();

        for (City city : cities) {
            responseList.add(new CityResponseDto(city.getCityId(),city.getCityName()));
        }

        return responseList;
	}

}