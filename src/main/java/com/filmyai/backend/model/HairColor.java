package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hair_colors")
public class HairColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hair_color_id", updatable = false, nullable = false)
    private Long hairColorId;

    @Column(name = "hair_color_name", nullable = false)
    private String hairColorName;

}