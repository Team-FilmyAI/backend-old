package com.filmyai.backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.backend.dto.ProjectLocationDto;
import com.filmyai.backend.exception.BadRequestException;
import com.filmyai.backend.exception.NotFoundException;
import com.filmyai.backend.model.City;
import com.filmyai.backend.model.Country;
import com.filmyai.backend.model.Project;
import com.filmyai.backend.model.ProjectLocation;
import com.filmyai.backend.model.State;
import com.filmyai.backend.repository.CityRepository;
import com.filmyai.backend.repository.CountryRepository;
import com.filmyai.backend.repository.ProjectLocationRepository;
import com.filmyai.backend.repository.ProjectRepository;
import com.filmyai.backend.repository.StateRepository;

@RestController
@RequestMapping("/api/auth/project-locations")
public class ProjectLocationController {

	@Autowired
	private ProjectLocationRepository locationRepo;

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;

	@GetMapping
	public List<ProjectLocation> getAllProjectLocations() {
		return locationRepo.findAll();
	}

	@GetMapping("/{id}")
	public ProjectLocation getProjectLocationById(@PathVariable Long id) {
		return locationRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Project location with id: " + id + " not found"));

	}

	@PostMapping
	public ProjectLocation createProjectLocation(@RequestBody ProjectLocationDto dto) {

		if (dto.getProjectId() == null)
			throw new BadRequestException("Project ID is required");
		if (dto.getCountryId() == null)
			throw new BadRequestException("Country ID is required");
		if (dto.getStateId() == null)
			throw new BadRequestException("State ID is required");
		if (dto.getCityId() == null)
			throw new BadRequestException("City ID is required");
		if (dto.getShootingStartDate() != null && dto.getShootingStartDate().isBefore(LocalDate.now()))
		    throw new BadRequestException("Shooting start date cannot be in the past");
		if (dto.getShootingEndDate() != null && dto.getShootingStartDate().isAfter(dto.getShootingEndDate()))
			throw new BadRequestException("Shooting start date cannot be after end date");

		Project project = projectRepo.findById(dto.getProjectId())
				.orElseThrow(() -> new NotFoundException("Project with id: " + dto.getProjectId() + " not found"));

		Country country = countryRepo.findById(dto.getCountryId())
				.orElseThrow(() -> new NotFoundException("Country with id: " + dto.getCountryId() + " not found"));

		State state = stateRepo.findById(dto.getStateId())
				.orElseThrow(() -> new NotFoundException("State with id: " + dto.getStateId() + " not found"));

		City city = cityRepo.findById(dto.getCityId())
				.orElseThrow(() -> new NotFoundException("City with id: " + dto.getCityId() + " not found"));

		ProjectLocation loc = new ProjectLocation();
		loc.setProject(project);
		loc.setCountry(country);
		loc.setState(state);
		loc.setCity(city);
		loc.setShootingStartDate(dto.getShootingStartDate());
		loc.setShootingEndDate(dto.getShootingEndDate());

		return locationRepo.save(loc);
	}

	@PutMapping("/{id}")
	public ProjectLocation updateProjectLocation(@PathVariable Long id, @RequestBody ProjectLocationDto dto) {

		ProjectLocation loc = locationRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("Project location with id: " + id + " not found"));

		if (dto.getProjectId() != null) {
			Project project = projectRepo.findById(dto.getProjectId())
					.orElseThrow(() -> new NotFoundException("Project with id: " + dto.getProjectId() + " not found"));
			loc.setProject(project);
		}

		if (dto.getCountryId() != null) {
			Country country = countryRepo.findById(dto.getCountryId())
					.orElseThrow(() -> new NotFoundException("Country with id: " + dto.getCountryId() + " not found"));
			loc.setCountry(country);
		}

		if (dto.getStateId() != null) {
			State state = stateRepo.findById(dto.getStateId())
					.orElseThrow(() -> new NotFoundException("State with id: " + dto.getStateId() + " not found"));
			loc.setState(state);
		}

		if (dto.getCityId() != null) {
			City city = cityRepo.findById(dto.getCityId())
					.orElseThrow(() -> new NotFoundException("City with id: " + dto.getCityId() + " not found"));
			loc.setCity(city);
		}

		if (dto.getShootingStartDate() != null)
			loc.setShootingStartDate(dto.getShootingStartDate());

		if (dto.getShootingEndDate() != null)
			loc.setShootingEndDate(dto.getShootingEndDate());
		
		if (loc.getShootingStartDate() != null && loc.getShootingStartDate().isBefore(LocalDate.now())) {
		    throw new BadRequestException("Shooting start date cannot be in the past");
		}
		
		if (loc.getShootingStartDate() != null && loc.getShootingEndDate() != null
				&& loc.getShootingStartDate().isAfter(loc.getShootingEndDate()))
			throw new BadRequestException("Shooting start date cannot be after end date");

		return locationRepo.save(loc);
	}

	@DeleteMapping("/{id}")
	public void deleteProjectLocation(@PathVariable Long id) {
		if (!locationRepo.existsById(id)) {
			throw new NotFoundException("Project location with id: " + id + " not found");
		}
		locationRepo.deleteById(id);
	}
}
