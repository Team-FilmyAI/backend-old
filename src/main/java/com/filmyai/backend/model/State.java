package com.filmyai.backend.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id", updatable = false, nullable = false)
    private Long stateId;

    @Column(name = "state_name", nullable = false, length = 100)
    private String stateName;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)  // Many states belong to one country
    @JoinColumn(
        name = "country_id", 
        nullable = false, 
        foreignKey = @ForeignKey(name = "fk_states_country")
    )
    private Country country;
    
}
