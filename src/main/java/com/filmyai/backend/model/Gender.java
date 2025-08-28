package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "genders")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id", updatable = false, nullable = false)
    private Long genderId;

    @Column(name = "gender_name", length = 32, nullable = false)
    private String genderName;

}