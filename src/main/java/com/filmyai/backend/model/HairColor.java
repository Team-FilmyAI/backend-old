package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hair_color")
public class HairColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hairColorId;

    @Column(nullable = false, unique = true)
    private String hairColorName;
   
}
