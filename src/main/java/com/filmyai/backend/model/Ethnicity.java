package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ethnicities")
public class Ethnicity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ethnicity_id", updatable = false, nullable = false)
    private Long ethnicityId;

    @Column(name = "ethnicity_name", length = 60, nullable = false)
    private String ethnicityName;

}