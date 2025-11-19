package com.filmyai.backend.controller;

import com.filmyai.backend.dto.ProjectList.ProjectResponseDto;
import com.filmyai.backend.service.ProjectList.ProjectsListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filmyai")
public class ProjectsListController {


    private final ProjectsListService projectsListService;

    @GetMapping("/projects")
    public List<ProjectResponseDto> getAllProjectsWithRoles() {
        return projectsListService.getAllProjectsWithRoles();
    }
}
