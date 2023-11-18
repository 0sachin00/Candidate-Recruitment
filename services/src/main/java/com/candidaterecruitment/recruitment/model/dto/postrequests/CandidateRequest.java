package com.candidaterecruitment.recruitment.model.dto.postrequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRequest {
    private String candidateName;
    private String candidateEmail;
    private String candidatePassword;

}
