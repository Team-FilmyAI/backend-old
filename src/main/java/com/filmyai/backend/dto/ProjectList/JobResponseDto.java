package com.filmyai.backend.dto.ProjectList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDto {
    private Long jobId;
    private String roleName;
    private String characterDescription;
    private Integer minAge;
    private Integer maxAge;
    private String gender;
    private String ethnicity;
    private String requirements;
    private String specialRequirements;
    private String languages;
    private Integer compensation;
    private String location;
    private LocalDate apply_by;
    private LocalDateTime postedAt;
}
