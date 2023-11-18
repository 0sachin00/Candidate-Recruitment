package com.candidaterecruitment.recruitment.service.serviceInterface;

import com.candidaterecruitment.recruitment.model.dto.postrequests.CandidateRequest;
import com.candidaterecruitment.recruitment.model.entity.Candidate;

import java.util.List;

public interface CandidateServiceInterface {
    public Candidate registerCandidate(CandidateRequest request);

    public List<Candidate> getAllCandidates();
}
