package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "jobs_writer")
public class JobsWriter {

    @Id
    @Column(name = "job_id", updatable = true, nullable = false)
    private Long jobId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "job_id", foreignKey = @ForeignKey(name = "fk_jobs_writer_id"))
    private Job job;

}