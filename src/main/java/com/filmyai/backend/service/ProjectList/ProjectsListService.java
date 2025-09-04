package com.filmyai.backend.service.ProjectList;


import com.filmyai.backend.dto.ProjectList.ProjectResponseDto;
import com.filmyai.backend.dto.ProjectList.JobResponseDto;
import com.filmyai.backend.exception.ResourceNotFoundException;
import com.filmyai.backend.model.Project;
import com.filmyai.backend.model.Job;
import com.filmyai.backend.model.JobsActor;
import com.filmyai.backend.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectsListService {

    private final ProjectRepository projectRepository;

    public List<ProjectResponseDto> getAllProjectsWithJobs() {

        List<Project> projects = Optional.ofNullable(projectRepository.findAllWithJobs())
                .orElse(List.of());

        return projects.stream().map(project -> ProjectResponseDto.builder()
                        .projectId(project.getProjectId())
                        .name(project.getName())
                        .synopsis(project.getSynopsis())
                        .startDate(project.getStartDate() != null ? project.getStartDate().toString() : null)
                        .endDate(project.getEndDate() != null ? project.getEndDate().toString() : null)
                        .genreName(project.getGenre() != null ? project.getGenre().getGenreName() : null)
                        .createdAt(project.getCreatedAt())
                        .status(project.getStatus())
                        .locations(project.getLocations())
                        .jobs(project.getJobs().stream().map(this::mapJobToDto).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }


    private JobResponseDto mapJobToDto(Job job) {
        JobsActor actor = job.getJobActor();

        return JobResponseDto.builder()
                .jobId(job.getJobId())
                .roleName(job.getRole() != null ? job.getRole().getRoleName() : null)
                .characterDescription(actor != null ? actor.getCharDescription() : null)
                .minAge(actor != null ? actor.getMinAge() : null)
                .maxAge(actor != null ? actor.getMaxAge() : null)
                .gender(actor != null && actor.getGender() != null ? actor.getGender().getGenderName() : null)
                .ethnicity(actor != null && actor.getEthnicity() != null ? actor.getEthnicity().getEthnicityName() : null)
                .requirements(actor != null ? actor.getRequirements() : null)
                .specialRequirements(actor != null ? actor.getSplRequirements() : null)
                .languages(actor != null ? actor.getLanguages() : null)
                .compensation(job.getCompensation() != null ? Integer.parseInt(job.getCompensation()) : null)
                .location(job.getLocation())
                .apply_by(actor != null && actor.getApplyBy() != null ? actor.getApplyBy().toLocalDate() : null)
                .postedAt(job.getPostedAt())
                .build();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findByIdWithJobs(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with id " + id + " not found"));
    }

    public ProjectResponseDto getProjectDtoById(Long id) {
        Project project = projectRepository.findByIdWithJobs(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with id " + id + " not found"));
        return mapProjectToDto(project);
    }

    private ProjectResponseDto mapProjectToDto(Project project) {
        return ProjectResponseDto.builder()
                .projectId(project.getProjectId())
                .name(project.getName())
                .synopsis(project.getSynopsis())
                .startDate(project.getStartDate() != null ? project.getStartDate().toString() : null)
                .endDate(project.getEndDate() != null ? project.getEndDate().toString() : null)
                .genreName(project.getGenre() != null ? project.getGenre().getGenreName() : null)
                .createdAt(project.getCreatedAt())
                .status(project.getStatus())
                .locations(project.getLocations())
                .jobs(project.getJobs().stream().map(this::mapJobToDto).collect(Collectors.toList()))
                .build();
    }


}