package com.candidaterecruitment.recruitment.model.dto.responseDetails;

import lombok.Data;

import java.util.List;

@Data
public class JobDetails {
    private long jobId;
    private String jobTitle;
    private String jobCompany;
    private String jobDescription;
    private String jobLocation;
    private float minJobExperience;
    private float maxJobExperience;
    private List<String> jobSkills;
    private String jobType;
    private Long recruiterId;
}
