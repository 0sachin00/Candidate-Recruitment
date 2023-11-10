package com.candidaterecruitment.recruitment.service.serviceInterface;

import com.candidaterecruitment.recruitment.model.dto.postrequests.JobRequest;
import com.candidaterecruitment.recruitment.model.entity.Job;

import java.util.List;

public interface JobServiceInterface {
    public List<Job> getAllJobs();
    public Job createJob(JobRequest request);
}
