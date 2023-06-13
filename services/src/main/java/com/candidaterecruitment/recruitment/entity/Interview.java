package com.candidaterecruitment.recruitment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "interviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interview {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String interviewId;

    @OneToOne
    @JoinColumn(name = "applied_job_id")
    private AppliedJob appliedJob;

    @Column(name = "type")
    private String interviewType;

    @Column(name = "round")
    private int interviewRound;

    @Column(name = "scheduled_date")
    private LocalDate interviewDate;

    @Column(name = "start_time")
    private LocalTime interviewStartTime;

    @Column(name = "end_time")
    private LocalTime interviewEndTime;

    @Column(name = "is_scheduled")
    private boolean interviewScheduled;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "feedbackCompleted", column = @Column(name = "feedback_completed")),
            @AttributeOverride(name = "feedbackMessage", column = @Column(name = "feedback_message"))
    })
    private Feedback feedback;
}
