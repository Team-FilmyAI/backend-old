package com.filmyai.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.List;
import java.util.Set;

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

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "synopsis")
    private String synopsis;


    @Column(name = "category", length = 150)
    private String category;

    @Lob
    @Column(name = "poster")
    private byte[] poster;

    @Lob
    @Column(name = "nda_document")
    private byte[] ndaDocument;

    @Column(name = "shoot_start_date")
    private LocalDateTime shootStartDate;

    @Column(name = "shoot_end_date")
    private LocalDateTime shootEndDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "shoot_start_location")
    private String shootStartLocation;

    @Column(name = "shoot_end_location")
    private String shootEndLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", foreignKey = @ForeignKey(name = "fk_projects_creator"))
    private User createdBy;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id") // this will populate project_id in jobs table
    private Set<Job> jobs;

    @ManyToMany
    @JoinTable(
            name = "projects_genres",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "projects_producers",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Producer> producers;
}