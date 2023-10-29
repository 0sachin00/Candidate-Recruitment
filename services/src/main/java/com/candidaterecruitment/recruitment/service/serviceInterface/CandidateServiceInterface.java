package com.candidaterecruitment.recruitment.service.serviceInterface;

import com.candidaterecruitment.recruitment.model.dto.CandidateRegistrationRequest;
import com.candidaterecruitment.recruitment.model.entity.Candidate;

import java.util.List;

public interface CandidateServiceInterface {
    public Candidate registerCandidate(CandidateRegistrationRequest request);

    public List<Candidate> getAllCandidates();
}
