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
    private String name;
    private String posterUrl;
    private String synopsis;
    private String startDate;
    private String endDate;
    private String genreName;
    private LocalDateTime createdAt;
    private String status;
    private String locations;

    private List<JobResponseDto> jobs;
}
