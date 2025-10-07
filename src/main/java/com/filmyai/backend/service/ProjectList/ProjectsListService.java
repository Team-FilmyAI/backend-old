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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectsListService {

    private final ProjectRepository projectRepository;

    public List<ProjectResponseDto> getAllProjectsWithJobs() {

        List<Project> projects = Optional.ofNullable(projectRepository.findAllWithJobs())
                .orElse(List.of());

        return projects.stream()
                .map(this::mapProjectToDto)
                .collect(Collectors.toList());
    }


    private JobResponseDto mapJobToDto(Job job) {
        if (job == null) return null;
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
    private ProjectResponseDto mapProjectToDto(Project project) {
        if (project == null) return null;


        List<JobResponseDto> jobDtos = project.getJobs() != null
                ? project.getJobs().stream()
                .map(this::mapJobToDto)
                .toList()
                : List.of();

        List<String> genreNames = project.getGenres() != null
                ? project.getGenres().stream()
                .map(g -> g.getGenreName())
                .toList()
                : List.of();

        List<String> producers = project.getProducers() != null
                ? project.getProducers().stream()
                .map(p -> p.getProductionCompany())
                .toList()
                : List.of();


        return ProjectResponseDto.builder()
                .projectId(project.getProjectId())
                .title(project.getTitle())
                .synopsis(project.getSynopsis())
                .category(project.getCategory())
                .poster(project.getPoster() != null ? Base64.getEncoder().encodeToString(project.getPoster()) : null)
                .ndaDocument(project.getNdaDocument() != null ? Base64.getEncoder().encodeToString(project.getNdaDocument()) : null)
                .createdAt(project.getCreatedAt())
                .status(project.getStatus())
                .shootStartLocation(project.getShootStartLocation())
                .shootEndLocation(project.getShootEndLocation())
                .shootStartDate(project.getShootStartDate() != null ? project.getShootStartDate().toString() : null)
                .shootEndDate(project.getShootEndDate() != null ? project.getShootEndDate().toString() : null)
                .jobs(jobDtos)
                .genreNames(genreNames)
                .producers(producers)
                .build();
    }


    public ProjectResponseDto getProjectDtoById(Long id) {
        Project project = projectRepository.findByIdWithJobs(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project with id " + id + " not found"));
        return mapProjectToDto(project);
    }


    public List<ProjectResponseDto> getProjectsByGenre(Long genreId) {
        List<Project> projects = projectRepository.findByGenreWithJobs(genreId);

        return projects.stream()
                .map(this::mapProjectToDto)
                .collect(Collectors.toList());
    }

    public List<ProjectResponseDto> getActiveProjectsByProducer(Long userId) {
        List<Project> projects = projectRepository.findActiveProjectsByProducer(userId);

        return projects.stream()
                .map(this::mapProjectToDto)
                .collect(Collectors.toList());
    }

}