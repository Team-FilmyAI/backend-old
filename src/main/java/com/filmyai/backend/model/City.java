package com.filmyai.backend.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @Column(nullable = false, unique = true)
    private String cityName;
    
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
}
