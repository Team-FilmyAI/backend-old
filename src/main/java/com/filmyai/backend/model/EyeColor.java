package com.filmyai.backend.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "eye_color")
public class EyeColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eyeColorId;

    @Column(nullable = false, unique = true)
    private String eyeColorName;
   
}
