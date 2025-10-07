package com.filmyai.backend.dto.ProjectList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProjectResponseDto {

    private Long projectId;
    private String title;
    private String synopsis;
    private LocalDateTime createdAt;
    private String category;
    private String poster;
    private String ndaDocument;
    private String shootStartDate;
    private String shootEndDate;
    private List<String> genreNames;
    private List<String> producers;
    private String status;
    private String shootStartLocation;
    private String shootEndLocation;

    private List<JobResponseDto> jobs;
}
