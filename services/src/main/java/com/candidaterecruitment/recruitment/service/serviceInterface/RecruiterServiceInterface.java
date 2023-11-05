package com.candidaterecruitment.recruitment.service.serviceInterface;

import com.candidaterecruitment.recruitment.model.dto.requests.CandidateRequest;
import com.candidaterecruitment.recruitment.model.dto.requests.RecruiterRequest;
import com.candidaterecruitment.recruitment.model.entity.Candidate;
import com.candidaterecruitment.recruitment.model.entity.Recruiter;

import java.util.List;

public interface RecruiterServiceInterface {
    public Recruiter registerRecruiter(RecruiterRequest request);
    public List<Recruiter> getAllRecruiters();
}
