package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "application_art_director")
public class ApplicationArtDirector {

    @Id
    @Column(name = "app_id", updatable = true, nullable = false)
    private Long appId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "app_id", foreignKey = @ForeignKey(name = "fk_app_art_id"))
    private Application app;

}