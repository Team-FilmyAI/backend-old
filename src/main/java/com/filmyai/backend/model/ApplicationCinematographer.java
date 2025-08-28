package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "application_cinematographer")
public class ApplicationCinematographer {

    @Id
    @Column(name = "app_id", updatable = true, nullable = false)
    private Long appId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "app_id", foreignKey = @ForeignKey(name = "fk_app_cine_id"))
    private Application app;

}