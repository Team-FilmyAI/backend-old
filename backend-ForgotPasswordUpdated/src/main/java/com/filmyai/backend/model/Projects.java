package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "projects")
public class Projects{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private Long projectId;

    private String title;

    @Column(name = "poster_url")
    private String posterUrl;

    private String synopsis;


    @ManyToOne
    @JoinColumn(name="category_id")
    private Categories category;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "project")
    private List<Roles> roles;

}