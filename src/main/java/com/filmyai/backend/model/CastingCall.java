package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "casting_calls")
public class CastingCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "call_id", updatable = false, nullable = false)
    private Long callId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", foreignKey = @ForeignKey(name = "fk_casting_application"))
    private Application application;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Column(name = "interview_mode", length = 50)
    private String interviewMode;

    @Column(name = "feedback")
    private String feedback;

}