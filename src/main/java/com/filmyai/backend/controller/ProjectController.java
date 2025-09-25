package com.filmyai.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.backend.dto.Project.ProjectRequestDto;
import com.filmyai.backend.dto.Project.ProjectResponseDto;
import com.filmyai.backend.service.Project.ProjectService;

@RestController
@RequestMapping("/filmyai")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    
//    @PutMapping("/projects/{id}")
//    public ResponseEntity<ProjectResponseDto> editProject(@PathVariable Long id) {
//        CountryResponseDto response = countryService.getCountryById(id);
//        return ResponseEntity.ok(response);
//    }
    
    @PutMapping("/projects/{projectId}")
    public ResponseEntity<ProjectResponseDto> editProject(@PathVariable Long projectId,
            @RequestBody ProjectRequestDto requestDto) {

        ProjectResponseDto updatedProject = projectService.editProject(projectId, requestDto);

        return ResponseEntity.ok(updatedProject);
    }
}