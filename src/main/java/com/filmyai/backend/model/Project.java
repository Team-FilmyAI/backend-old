package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", updatable = false, nullable = false)
    private Long projectId;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "genre", columnDefinition = "TEXT[]")
    private String[] genre;
    
    @Column(name = "synopsis", columnDefinition = "TEXT")
    private String synopsis;

    @Column(length = 150)
    private String category;

    @Column(name = "production_companies", columnDefinition = "TEXT[]")
    private String[] productionCompanies;

    @Lob
    @Column(name = "poster")
    private byte[] poster;
    
    @Lob
    @Column(name = "nda_document")
    private byte[] ndaDocument;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
    
    @Column(name = "shoot_start_date")
    private LocalDate shootStartDate;

    @Column(name = "shoot_start_location", length = 255)
    private String shootStartLocation;

    @Column(name = "shoot_end_date")
    private LocalDate shootEndDate;

    @Column(name = "shoot_end_location", length = 255)
    private String shootEndLocation;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

}