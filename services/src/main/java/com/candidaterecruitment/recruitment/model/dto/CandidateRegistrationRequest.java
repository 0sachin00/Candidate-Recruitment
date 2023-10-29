package com.candidaterecruitment.recruitment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRegistrationRequest {
    private String candidateName;
    private String candidateEmail;
    private String candidatePassword;

}
