package com.candidaterecruitment.recruitment.service.serviceInterface;

import com.candidaterecruitment.recruitment.entity.Job;

import java.util.List;

public interface JobServiceInterface {
    public List<Job> getAllJobs();
//    public Job getJobById(String id);
    public Job saveJob(Job job);
    public void deleteJob(String id);
}
