package com.filmyai.backend.dto.Country;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryResponseDto {

	private Long countryId;
	private String countryName;
}
