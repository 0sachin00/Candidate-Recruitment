package com.candidaterecruitment.recruitment.model.dto.postrequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruiterAuthenticationRequest {
    private String recruiterEmail;
    private String recruiterPassword;
}
