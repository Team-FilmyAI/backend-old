package com.filmyai.backend.service.Country;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filmyai.backend.dto.Country.CountryResponseDto;
import com.filmyai.backend.model.Country;
import com.filmyai.backend.repository.CountryRepository;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryResponseDto> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        List<CountryResponseDto> responseList = new ArrayList<>();

        for (Country country : countries) {
            responseList.add(new CountryResponseDto(country.getCountryId(), country.getCountryName()));
        }

        return responseList;
    }
    
    public CountryResponseDto getCountryById(Long id) {
        Country country = countryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Country with id: " + id + " not found"));

        return new CountryResponseDto(country.getCountryId(), country.getCountryName());
    }

}