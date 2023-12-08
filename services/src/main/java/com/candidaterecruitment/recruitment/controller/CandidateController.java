package com.candidaterecruitment.recruitment.controller;

import com.candidaterecruitment.recruitment.model.dto.getresponses.JobResponse;
import com.candidaterecruitment.recruitment.model.dto.responsedetails.getresponsedetails.CandidateGetResponseDetails;
import com.candidaterecruitment.recruitment.model.dto.postrequests.CandidateRequest;
import com.candidaterecruitment.recruitment.model.dto.responsedetails.getresponsedetails.JobGetResponseDetails;
import com.candidaterecruitment.recruitment.model.dto.responsedetails.postresponsedetails.CandidatePostResponseDetails;
import com.candidaterecruitment.recruitment.model.dto.getresponses.CandidateResponse;
import com.candidaterecruitment.recruitment.model.entity.Candidate;
import com.candidaterecruitment.recruitment.model.entity.Job;
import com.candidaterecruitment.recruitment.service.serviceImplementation.CandidateServiceImplementation;
import com.candidaterecruitment.recruitment.customexceptions.CandidateRegistrationException;
import com.candidaterecruitment.recruitment.service.serviceImplementation.JobServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    @Autowired
    public CandidateServiceImplementation candidateServiceImplementation;

    @Autowired
    private JobServiceImplementation jobServiceImplementation;



    @GetMapping("/all-jobs")
    public ResponseEntity<JobResponse> getAllJobs(){
        List<Job> jobs = jobServiceImplementation.getAllJobs();
        List<JobGetResponseDetails> responseList = jobs.stream()
                .map(job -> {
                    JobGetResponseDetails response = new JobGetResponseDetails();
                    response.setJobId(job.getJobId());
                    response.setJobTitle(job.getJobTitle());
                    response.setJobCompany(job.getJobCompany());
                    response.setJobDescription(job.getJobDescription());
                    response.setJobLocation(job.getJobLocation());
                    response.setMinJobExperience(job.getMinJobExperience());
                    response.setMaxJobExperience(job.getMaxJobExperience());
                    response.setJobSkills(job.getJobSkills());
                    response.setJobType(job.getJobType());
                    response.setRecruiterId(job.getRecruiter().getRecruiterId());
                    return response;
                })
                .collect(Collectors.toList());
        JobResponse response = new JobResponse();
        response.setRes(responseList);
        return ResponseEntity.ok(response);
    }
}
