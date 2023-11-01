package com.candidaterecruitment.recruitment.model.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruiterRequest {
    private String recruiterName;
    private String recruiterEmail;
    private String recruiterPassword;
}
