package com.filmyai.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jobs_director")
public class JobsDirector {

    @Id
    @Column(name = "job_id", updatable = true, nullable = false)
    private Long jobId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "job_id", foreignKey = @ForeignKey(name = "fk_jobs_director_id"))
    private Job job;

}