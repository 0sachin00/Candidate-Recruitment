package com.candidaterecruitment.recruitment.controller;

import com.candidaterecruitment.recruitment.model.entity.Job;
import com.candidaterecruitment.recruitment.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public void createJobs(){

    }

    public void deleteJobs(){

    }
}
