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
    private String posterUrl;
    private String synopsis;
    private String categoryName;
    private LocalDateTime createdAt;

    private List<RoleResponseDto> roles;
 }
