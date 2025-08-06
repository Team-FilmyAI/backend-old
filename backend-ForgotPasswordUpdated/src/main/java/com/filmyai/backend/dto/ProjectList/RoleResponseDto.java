package com.filmyai.backend.dto.ProjectList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto {
    private Long roleId;
    private String roleName;
    private String characterDescription;
    private Integer minAge;
    private Integer maxAge;
    private String gender;
    private String currency;
    private Integer compensation;
    private BigDecimal compensationBonus;
    private LocalDate deadlineToApply;
}
