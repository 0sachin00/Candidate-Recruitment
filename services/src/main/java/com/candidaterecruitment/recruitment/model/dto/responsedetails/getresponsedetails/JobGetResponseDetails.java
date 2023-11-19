package com.candidaterecruitment.recruitment.model.dto.responsedetails.getresponsedetails;

import lombok.Data;

import java.util.List;

@Data
public class JobGetResponseDetails {
    private String jobId;
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
