package com.candidaterecruitment.recruitment.service.serviceImplementation;

import com.candidaterecruitment.recruitment.model.entity.Job;
import com.candidaterecruitment.recruitment.repository.JobRepository;
import com.candidaterecruitment.recruitment.service.serviceInterface.JobServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImplementation implements JobServiceInterface {
    @Autowired
    JobRepository jobRepository;

    @Override
    public List<Job> getAllJobs() {
        return null;
    }

//    @Override
//    public Job getJobById(String id) {
//        return null;
//    }

    @Override
    public Job saveJob(Job job) {
        return null;
    }

    @Override
    public void deleteJob(String id) {

    }
}
