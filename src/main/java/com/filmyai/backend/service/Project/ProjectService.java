package com.filmyai.backend.service.Project;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.filmyai.backend.dto.Project.ProjectRequestDto;
import com.filmyai.backend.dto.Project.ProjectResponseDto;
import com.filmyai.backend.model.Project;
import com.filmyai.backend.repository.CityRepository;
import com.filmyai.backend.repository.CountryRepository;
import com.filmyai.backend.repository.ProjectRepository;
import com.filmyai.backend.repository.StateRepository;

@Service
public class ProjectService {

	/**
	 * Edit/update an existing project.
	 *
	 * @param projectId  the ID of the project to edit
	 * @param requestDto the data to update
	 * @return updated ProjectResponseDto
	 */

	private final ProjectRepository projectRepository;
	private final CountryRepository countryRepository;
	private final StateRepository stateRepository;
	private final CityRepository cityRepository;

	public ProjectService(ProjectRepository projectRepository, CountryRepository countryRepository,
			StateRepository stateRepository, CityRepository cityRepository) {
		this.projectRepository = projectRepository;
		this.countryRepository = countryRepository;
		this.stateRepository = stateRepository;
		this.cityRepository = cityRepository;
	}

	@Transactional
	public ProjectResponseDto editProject(Long projectId, ProjectRequestDto requestDto) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new RuntimeException("Project with ID: " + projectId + " not found"));

		project.setTitle(requestDto.getTitle());
		project.setGenre(requestDto.getGenre());
		project.setSynopsis(requestDto.getSynopsis());
		project.setCategory(requestDto.getCategory());
		project.setProductionCompanies(requestDto.getProductionCompanies());
		project.setPoster(requestDto.getPoster());
		project.setNdaDocument(requestDto.getNdaDocument());
		project.setShootStartDate(requestDto.getShootStartDate());
		project.setShootStartLocation(requestDto.getShootStartLocation());
		project.setShootEndDate(requestDto.getShootEndDate());
		project.setShootEndLocation(requestDto.getShootEndLocation());

		if (requestDto.getCountryId() != null) {
			project.setCountry(countryRepository.findById(requestDto.getCountryId()).orElse(null));
		}
		if (requestDto.getStateId() != null) {
			project.setState(stateRepository.findById(requestDto.getStateId()).orElse(null));
		}
		if (requestDto.getCityId() != null) {
			project.setCity(cityRepository.findById(requestDto.getCityId()).orElse(null));
		}

		Project updatedProject = projectRepository.save(project);

		return mapProjectToDto(updatedProject);
	}

	private ProjectResponseDto mapProjectToDto(Project project) {
		return ProjectResponseDto.builder()
				.projectId(project.getProjectId())
				.title(project.getTitle())
				.genre(project.getGenre())
				.synopsis(project.getSynopsis())
				.category(project.getCategory())
				.productionCompanies(project.getProductionCompanies())
				.poster(project.getPoster())
				.ndaDocument(project.getNdaDocument())
				.countryId(project.getCountry() != null ? project.getCountry().getCountryId() : null)
				.stateId(project.getState() != null ? project.getState().getStateId() : null)
				.cityId(project.getCity() != null ? project.getCity().getCityId() : null)
				.shootStartDate(project.getShootStartDate())
				.shootStartLocation(project.getShootStartLocation())
				.shootEndDate(project.getShootEndDate())
				.shootEndLocation(project.getShootEndLocation())
				.createdAt(project.getCreatedAt())
				.build();
	}
}