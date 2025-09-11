package com.filmyai.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.backend.dto.State.StateResponseDto;
import com.filmyai.backend.service.State.StateService;

@RestController
@RequestMapping("/filmyai")
public class StateController {

	private final StateService stateService;

	public StateController(StateService stateService) {
		this.stateService = stateService;
	}

	@GetMapping("/states")
	public ResponseEntity<List<StateResponseDto>> getAllStates() {
		return ResponseEntity.ok(stateService.getAllStates());
	}

	@GetMapping("/states/{stateId}")
	public ResponseEntity<StateResponseDto> getStateById(@PathVariable Long stateId) {
		StateResponseDto response = stateService.getStateById(stateId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/countries/{countryId}/states")
	public ResponseEntity<List<StateResponseDto>> getStatesByCountryId(@PathVariable Long countryId) {
		List<StateResponseDto> states = stateService.getStatesByCountryId(countryId);
		return ResponseEntity.ok(states);
	}
}