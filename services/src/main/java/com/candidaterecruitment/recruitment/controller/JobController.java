package com.candidaterecruitment.recruitment.controller;

import com.candidaterecruitment.recruitment.customexceptions.JobCreationException;;
import com.candidaterecruitment.recruitment.model.dto.requests.JobRequest;
import com.candidaterecruitment.recruitment.model.dto.responseDetails.JobDetails;
import com.candidaterecruitment.recruitment.model.dto.responses.JobResponse;
import com.candidaterecruitment.recruitment.model.entity.Job;
import com.candidaterecruitment.recruitment.service.serviceImplementation.JobServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    private JobServiceImplementation jobServiceImplementation;

    @GetMapping("/allJobs")
    public ResponseEntity<JobResponse> getAllJobDetails(){
        List<Job> jobs = jobServiceImplementation.getAllJobs();
        List<JobDetails> jobDetails = jobs.stream()
                .map(job -> {
                    JobDetails jobDetails1 = new JobDetails();
                    jobDetails1.setJobId(job.getJobId());
                    jobDetails1.setJobTitle(job.getJobTitle());
                    jobDetails1.setJobCompany(job.getJobCompany());
                    jobDetails1.setJobDescription(job.getJobDescription());
                    jobDetails1.setJobLocation(job.getJobLocation());
                    jobDetails1.setMinJobExperience(job.getMinJobExperience());
                    jobDetails1.setMaxJobExperience(job.getMaxJobExperience());
                    jobDetails1.setJobSkills(job.getJobSkills());
                    jobDetails1.setJobType(job.getJobType());
                    jobDetails1.setRecruiterId(job.getRecruiter().getRecruiterId());
                    return jobDetails1;
                })
                .collect(Collectors.toList());
        JobResponse jobResponse = new JobResponse();
        jobResponse.setRes(jobDetails);
        return ResponseEntity.ok(jobResponse);
    }

    @PostMapping("/createJob")
    public ResponseEntity<String> createJobs(@RequestBody JobRequest request){
        try{
            Job createdJob = jobServiceImplementation.createJob(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Job created successfully. Job ID: " + createdJob.getJobId());
        }catch (JobCreationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
