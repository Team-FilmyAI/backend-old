package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "eye_colors")
public class EyeColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eye_color_id", updatable = false, nullable = false)
    private Long eyeColorId;

    @Column(name = "eye_color_name", length = 40, nullable = false)
    private String eyeColorName;

}