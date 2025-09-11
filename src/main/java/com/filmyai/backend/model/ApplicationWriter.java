package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "application_writer")
public class ApplicationWriter {

    @Id
    @Column(name = "app_id", updatable = true, nullable = false)
    private Long appId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "app_id", foreignKey = @ForeignKey(name = "fk_app_writer_id"))
    private Application app;

}