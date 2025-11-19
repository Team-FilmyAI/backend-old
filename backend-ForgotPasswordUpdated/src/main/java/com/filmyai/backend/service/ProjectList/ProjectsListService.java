package com.filmyai.backend.service.ProjectList;


import com.filmyai.backend.dto.ProjectList.ProjectResponseDto;
import com.filmyai.backend.dto.ProjectList.RoleResponseDto;
import com.filmyai.backend.model.Projects;
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

    public List<ProjectResponseDto> getAllProjectsWithRoles() {

        List<Projects> projects = Optional.ofNullable(projectRepository.findAllWithRoles())
                .orElse(List.of()); // custom JOIN fetch query recommended

        return projects.stream().map(project -> ProjectResponseDto.builder()
                .projectId(project.getProjectId())
                .title(project.getTitle())
                .posterUrl(project.getPosterUrl())
                .synopsis(project.getSynopsis())
                .categoryName(project.getCategory() != null ? project.getCategory().getCategoryName() : null) // assuming relationship exists
                .createdAt(project.getCreatedAt())
                .roles(project.getRoles().stream().map(role -> RoleResponseDto.builder()
                                .roleId(role.getRoleId())
                                .roleName(role.getRoleName())
                                .characterDescription(role.getCharacterDescription())
                                .minAge(role.getMinAge())
                                .maxAge(role.getMaxAge())
                                .gender(role.getGender() != null ? role.getGender().getGenderName() : null)        // assuming a Gender entity
                                .currency(role.getCurrency() != null ? role.getCurrency().getCurrencyName() : null)    // assuming a Currency entity
                                .compensation(role.getCompensation())
                                .compensationBonus(role.getCompensationBonus())
                                .deadlineToApply(role.getDeadlineToApply())
                                .build())
                        .collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
    }
}
