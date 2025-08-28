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
    @Column(name = "city_id", updatable = false, nullable = false)
    private Long cityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_cities_state"))
    private State state;

    @Column(name = "city_name", length = 100, nullable = false)
    private String cityName;

}