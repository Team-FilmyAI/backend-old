package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "actors")
public class Actor {

    @Id
    @Column(name = "user_id", updatable = true, nullable = false)
    private Long userId;

    @Column(name = "portfolio_link")
    private String portfolioLink;

    @Column(name = "agency", length = 100)
    private String agency;

    @Column(name = "languages")
    private String languages;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "nationality", length = 50)
    private String nationality;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "headshots")
    private String headshots;

    @Column(name = "availability", length = 50)
    private String availability;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_actors_user"))
    private User user;

}