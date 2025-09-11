package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "producers")
public class Producer {

    @Id
    @Column(name = "user_id", updatable = true, nullable = false)
    private Long userId;

    @Column(name = "production_company", length = 255)
    private String productionCompany;

    @Column(name = "role", length = 100)
    private String role;

    @Column(name = "notable_projects")
    private String notableProjects;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "availability", length = 50)
    private String availability;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_producers_user"))
    private User user;

}