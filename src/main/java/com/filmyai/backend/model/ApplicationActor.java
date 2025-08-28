package com.filmyai.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "application_actor")
public class ApplicationActor {

    @Id
    @Column(name = "app_id", updatable = true, nullable = false)
    private Long appId;

    @Column(name = "loc_availability")
    private Boolean locAvailability;

    @Column(name = "dob")
    private LocalDateTime dob;

    @Column(name = "age")
    private Long age;

    @Column(name = "height", length = 20)
    private String height;

    @Column(name = "weight", length = 20)
    private String weight;

    @Column(name = "languages")
    private String languages;

    @Column(name = "years_exp")
    private Long yearsExp;

    @Column(name = "act_education")
    private String actEducation;

    @Column(name = "previous_role")
    private String previousRole;

    @Column(name = "ind_exp")
    private Long indExp;

    @Column(name = "projects")
    private String projects;

    @Column(name = "resume_mime")
    private String resumeMime;

    @Lob
    @Column(name = "resume_data")
    private byte[] resumeData;

    @Column(name = "resume_filename")
    private String resumeFilename;

    @Column(name = "resume_size")
    private Long resumeSize;

    @Column(name = "headshots_mime")
    private String headshotsMime;

    @Lob
    @Column(name = "headshots_data")
    private byte[] headshotsData;

    @Column(name = "headshots_filename")
    private String headshotsFilename;

    @Column(name = "headshots_size")
    private Long headshotsSize;

    @Column(name = "reel_mime")
    private String reelMime;

    @Lob
    @Column(name = "reel_data")
    private byte[] reelData;

    @Column(name = "reel_filename")
    private String reelFilename;

    @Column(name = "reel_size")
    private Long reelSize;

    @Column(name = "prof_url")
    private String profUrl;

    @Column(name = "availability")
    private Boolean availability;

    @Column(name = "conflict")
    private Boolean conflict;

    @Column(name = "role_interest")
    private String roleInterest;

    @Column(name = "char_relate")
    private String charRelate;

    @Column(name = "exp_pay")
    private Long expPay;

    @Column(name = "skills")
    private String skills;

    @Column(name = "talents")
    private String talents;

    @Column(name = "license")
    private String license;

    @Column(name = "voiceover")
    private String voiceover;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id", foreignKey = @ForeignKey(name = "fk_app_actor_gender"))
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eye_color_id", foreignKey = @ForeignKey(name = "fk_app_actor_eye"))
    private EyeColor eyeColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hair_color_id", foreignKey = @ForeignKey(name = "fk_app_actor_hair"))
    private HairColor hairColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ethnicity_id", foreignKey = @ForeignKey(name = "fk_app_actor_eth"))
    private Ethnicity ethnicity;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "app_id", foreignKey = @ForeignKey(name = "fk_app_actor_id"))
    private Application app;

}