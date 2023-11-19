package com.candidaterecruitment.recruitment.model.dto.postrequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {
    private String jobTitle;
    private String jobCompany;
    private String jobDescription;
    private String jobLocation;
    private float minJobExperience;
    private float maxJobExperience;
    private List<String> jobSkills;
    private String jobType;
    private String recruiterId;
}
