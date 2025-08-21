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

    @Column(name = "city_name", nullable = false, length = 100)
    private String cityName;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)  // Many cities belong to one state
    @JoinColumn(
        name = "state_id", 
        nullable = false, 
        foreignKey = @ForeignKey(name = "fk_cities_state")
    )
    private State state;
}
