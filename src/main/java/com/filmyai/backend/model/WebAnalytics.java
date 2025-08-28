package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "web_analytics")
public class WebAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", updatable = false, nullable = false)
    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_wa_user"))
    private User user;

    @Column(name = "event_type", length = 100)
    private String eventType;

    @Column(name = "event_target", length = 100)
    private String eventTarget;

    @Column(name = "ts", nullable = false)
    private LocalDateTime ts;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

}