package com.filmyai.backend.dto.City;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityResponseDto {

	private Long cityId;
	private String cityName;
}
