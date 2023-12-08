package com.candidaterecruitment.recruitment.model.dto.postrequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateAuthenticationRequest {
    private String candidateEmail;
    private String candidatePassword;
}
