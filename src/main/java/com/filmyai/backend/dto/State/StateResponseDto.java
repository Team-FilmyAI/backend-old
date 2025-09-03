package com.filmyai.backend.dto.State;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StateResponseDto {

	private Long stateId;
	private String stateName;
}
