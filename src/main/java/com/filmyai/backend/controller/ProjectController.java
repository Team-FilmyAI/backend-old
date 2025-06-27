package com.filmyai.backend.controller;

import com.filmyai.backend.model.Project;
import com.filmyai.backend.model.ProjectLocation;
import com.filmyai.backend.repository.ProjectRepository;
import com.filmyai.backend.dto.ProjectCreateDto;
import com.filmyai.backend.dto.ProjectUpdateDto;
import com.filmyai.backend.exception.NotFoundException;
import com.filmyai.backend.model.Category;
import com.filmyai.backend.model.MyAppUser;
import com.filmyai.backend.repository.CategoryRepository;
import com.filmyai.backend.repository.ProjectLocationRepository;
import com.filmyai.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/auth/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private ProjectLocationRepository locationRepository;

    
    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Project with id: " + id + " not found"));
    }

    @PostMapping
    public Project createProject(@RequestBody ProjectCreateDto dto, Principal principal) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category with id: " + dto.getCategoryId() + " not found"));

        
//        When JWT token is implemented, then this needed to be tested
//        MyAppUser user = myAppUserRepository.findByEmail(principal.getName())
//            .orElseThrow(() -> new RuntimeException("User not found"));
//        For testing purposes, using hardcoded values
        
        MyAppUser user = userRepository.findById(1L)
        	    .orElseThrow(() -> new RuntimeException("User not found"));
        
        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setPosterUrl(dto.getPosterUrl());
        project.setSynopsis(dto.getSynopsis());
        project.setCategory(category);
        project.setCreatedBy(user);
        
        return projectRepository.save(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody ProjectUpdateDto dto, Principal principal) {
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Project with id: " + id + " not found"));
        
        // Strings: update only if not null / not blank
        if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
            project.setTitle(dto.getTitle());
        }

        if (dto.getPosterUrl() != null && !dto.getPosterUrl().isBlank()) {
            project.setPosterUrl(dto.getPosterUrl());
        }

        if (dto.getSynopsis() != null && !dto.getSynopsis().isBlank()) {
            project.setSynopsis(dto.getSynopsis());
        }

        if (dto.getLocationId() != null) {
            ProjectLocation location = locationRepository.findById(dto.getLocationId())
                    .orElseThrow(() -> new NotFoundException("Location with id: "+ dto.getLocationId() +" not found"));
            project.setLocation(location);
        }

        // Category: update only if a new ID is supplied
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category with id: "+ dto.getCategoryId() +" not found"));
            project.setCategory(category);
        }
        
        return projectRepository.save(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException("Project with id: " + id + " not found");
        }
        projectRepository.deleteById(id);
    }
}
