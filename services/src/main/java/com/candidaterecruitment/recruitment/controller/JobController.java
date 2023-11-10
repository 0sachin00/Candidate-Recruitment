package com.candidaterecruitment.recruitment.controller;

import com.candidaterecruitment.recruitment.customexceptions.JobCreationException;;
import com.candidaterecruitment.recruitment.model.dto.postrequests.JobRequest;
import com.candidaterecruitment.recruitment.model.dto.responsedetails.getresponsedetails.JobGetResponseDetails;
import com.candidaterecruitment.recruitment.model.dto.getresponses.JobResponse;
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
        List<JobGetResponseDetails> jobGetResponseDetails = jobs.stream()
                .map(job -> {
                    JobGetResponseDetails jobGetResponseDetails1 = new JobGetResponseDetails();
                    jobGetResponseDetails1.setJobId(job.getJobId());
                    jobGetResponseDetails1.setJobTitle(job.getJobTitle());
                    jobGetResponseDetails1.setJobCompany(job.getJobCompany());
                    jobGetResponseDetails1.setJobDescription(job.getJobDescription());
                    jobGetResponseDetails1.setJobLocation(job.getJobLocation());
                    jobGetResponseDetails1.setMinJobExperience(job.getMinJobExperience());
                    jobGetResponseDetails1.setMaxJobExperience(job.getMaxJobExperience());
                    jobGetResponseDetails1.setJobSkills(job.getJobSkills());
                    jobGetResponseDetails1.setJobType(job.getJobType());
                    jobGetResponseDetails1.setRecruiterId(job.getRecruiter().getRecruiterId());
                    return jobGetResponseDetails1;
                })
                .collect(Collectors.toList());
        JobResponse jobResponse = new JobResponse();
        jobResponse.setRes(jobGetResponseDetails);
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
