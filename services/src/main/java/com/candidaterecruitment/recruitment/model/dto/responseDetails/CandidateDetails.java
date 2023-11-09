package com.candidaterecruitment.recruitment.model.dto.responseDetails;

import lombok.Data;

import java.util.UUID;

@Data
public class CandidateDetails {
    private UUID id;
    private String candidateName;
    private String candidateEmail;
}
