package com.candidaterecruitment.recruitment.service.serviceImplementation;

import com.candidaterecruitment.recruitment.customexceptions.JobCreationException;
import com.candidaterecruitment.recruitment.model.dto.postrequests.JobRequest;
import com.candidaterecruitment.recruitment.model.entity.AppliedJob;
import com.candidaterecruitment.recruitment.model.entity.Job;
import com.candidaterecruitment.recruitment.model.entity.Recruiter;
import com.candidaterecruitment.recruitment.repository.JobRepository;
import com.candidaterecruitment.recruitment.repository.RecruiterRepository;
import com.candidaterecruitment.recruitment.service.serviceInterface.AppliedJobServiceInterface;
import com.candidaterecruitment.recruitment.service.serviceInterface.JobServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppliedJobServiceImplementation implements AppliedJobServiceInterface {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    RecruiterRepository recruiterRepository;

    @Override
    public List<AppliedJob> getAllAppliedJobs() {
        return null;
    }
}
