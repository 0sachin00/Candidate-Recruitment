package com.candidaterecruitment.recruitment.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Feedback {
    private boolean feedbackCompleted;
    private String feedbackMessage;
}
