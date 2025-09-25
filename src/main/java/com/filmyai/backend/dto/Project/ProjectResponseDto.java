package com.filmyai.backend.dto.Project;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponseDto {
    
    private Long projectId;
    private String title;
    private String[] genre;
    private String synopsis;
    private String category;
    private String[] productionCompanies;
    private byte[] poster;
    private byte[] ndaDocument;
    private Long countryId;
    private Long stateId;
    private Long cityId;
    private LocalDate shootStartDate;
    private String shootStartLocation;
    private LocalDate shootEndDate;
    private String shootEndLocation;
    private LocalDateTime createdAt;

}
