package com.filmyai.backend.controller;

import com.filmyai.backend.dto.ProjectList.ProjectResponseDto;
import com.filmyai.backend.model.Project;
import com.filmyai.backend.service.ProjectList.ProjectsListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filmyai")
public class ProjectsListController {

    private final ProjectsListService projectsListService;

//    @GetMapping("/projects")
//    public ResponseEntity<List<ProjectResponseDto>> getAllProjectsWithJobs() {
//        List<ProjectResponseDto> projects = projectsListService.getAllProjectsWithJobs();
//        return ResponseEntity.ok(projects);
//    }
//
//    @GetMapping("/projects/{id}")
//    public ResponseEntity<ProjectResponseDto> getProjectById(@PathVariable Long id) {
//        ProjectResponseDto projectDto = projectsListService.getProjectDtoById(id);
//        return ResponseEntity.ok(projectDto);
//    }
//
//    @GetMapping("/projects/genre/{genreId}")
//    public ResponseEntity<List<ProjectResponseDto>> getProjectsByGenre(@PathVariable Long genreId) {
//        List<ProjectResponseDto> projects = projectsListService.getProjectsByGenre(genreId);
//        return ResponseEntity.ok(projects);
//    }
}
