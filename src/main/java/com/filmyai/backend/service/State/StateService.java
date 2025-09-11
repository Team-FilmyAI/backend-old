package com.filmyai.backend.service.State;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filmyai.backend.dto.State.StateResponseDto;
import com.filmyai.backend.model.State;
import com.filmyai.backend.repository.StateRepository;

@Service
public class StateService {

	private final StateRepository stateRepository;

	public StateService(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}

	public List<StateResponseDto> getAllStates() {
		List<State> states = stateRepository.findAll();
		List<StateResponseDto> responseList = new ArrayList<>();

		for (State state : states) {
			responseList.add(new StateResponseDto(state.getStateId(), state.getStateName()));
		}

		return responseList;
	}

	public StateResponseDto getStateById(Long id) {
		State state = stateRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("State with id: " + id + " not found"));

		return new StateResponseDto(state.getStateId(), state.getStateName());
	}

	public List<StateResponseDto> getStatesByCountryId(Long countryId) {
		List<State> states = stateRepository.findByCountry_CountryId(countryId);
		List<StateResponseDto> responseList = new ArrayList<>();

		for (State state : states) {
			responseList.add(new StateResponseDto(state.getStateId(), state.getStateName()));
		}

		return responseList;
	}

}
