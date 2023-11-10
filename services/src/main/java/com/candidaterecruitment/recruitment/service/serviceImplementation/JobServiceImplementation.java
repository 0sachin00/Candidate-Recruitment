package com.candidaterecruitment.recruitment.service.serviceImplementation;

import com.candidaterecruitment.recruitment.customexceptions.JobCreationException;
import com.candidaterecruitment.recruitment.model.dto.postrequests.JobRequest;
import com.candidaterecruitment.recruitment.model.entity.Job;
import com.candidaterecruitment.recruitment.model.entity.Recruiter;
import com.candidaterecruitment.recruitment.repository.JobRepository;
import com.candidaterecruitment.recruitment.repository.RecruiterRepository;
import com.candidaterecruitment.recruitment.service.serviceInterface.JobServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImplementation implements JobServiceInterface {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    RecruiterRepository recruiterRepository;

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(JobRequest request){
        Recruiter recruiter = recruiterRepository.findById(request.getRecruiterId())
                .orElseThrow(() -> new JobCreationException("Recruiter not found with ID: " + request.getRecruiterId()));;

        Job job = new Job();
        job.setJobTitle(request.getJobTitle());
        job.setJobCompany(request.getJobCompany());
        job.setJobDescription(request.getJobDescription());
        job.setJobLocation(request.getJobLocation());
        job.setMinJobExperience(request.getMinJobExperience());
        job.setMaxJobExperience(request.getMaxJobExperience());
        job.setJobSkills(request.getJobSkills());
        job.setJobType(request.getJobType());
        job.setRecruiter(recruiter);

        return jobRepository.save(job);
    }

}
