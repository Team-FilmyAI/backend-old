package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jobs_actor")
public class JobsActor {

    @Id
    @Column(name = "job_id", updatable = true, nullable = false)
    private Long jobId;

    @Column(name = "char_description")
    private String charDescription;

    @Column(name = "min_age")
    private Integer minAge;

    @Column(name = "max_age")
    private Integer maxAge;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "spl_requirements")
    private String splRequirements;

    @Column(name = "languages")
    private String languages;

    @Column(name = "apply_by")
    private LocalDateTime applyBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id", foreignKey = @ForeignKey(name = "fk_jobs_actor_gen"))
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ethnicity_id", foreignKey = @ForeignKey(name = "fk_jobs_actor_eth"))
    private Ethnicity ethnicity;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "job_id", foreignKey = @ForeignKey(name = "fk_jobs_actor_id"))
    private Job job;

}