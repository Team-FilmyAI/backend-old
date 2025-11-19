package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Projects project;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "character_description")
    private String characterDescription;

    @Column(name = "min_age")
    private Integer minAge;

    @Column(name = "max_age")
    private Integer maxAge;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Genders gender;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currencies currency;


    private Integer compensation;

    @Column(name = "compensation_bonus")
    private BigDecimal compensationBonus;

    @Column(name = "deadline_to_apply")
    private LocalDate deadlineToApply;

}
