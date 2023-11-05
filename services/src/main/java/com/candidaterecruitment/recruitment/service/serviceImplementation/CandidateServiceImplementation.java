package com.candidaterecruitment.recruitment.service.serviceImplementation;

import com.candidaterecruitment.recruitment.model.dto.requests.CandidateRequest;
import com.candidaterecruitment.recruitment.model.entity.Candidate;
import com.candidaterecruitment.recruitment.repository.CandidateRepository;
import com.candidaterecruitment.recruitment.customexceptions.CandidateRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImplementation {
    @Autowired
    public CandidateRepository candidateRepository;

    public Candidate registerCandidate(CandidateRequest request) {
        if (candidateRepository.findByCandidateEmail(request.getCandidateEmail()).isPresent()) {
            throw new CandidateRegistrationException("Username or email is already in use.");
        }

        Candidate candidate = new Candidate();
        candidate.setCandidateName(request.getCandidateName());
        candidate.setCandidateEmail(request.getCandidateEmail());
        candidate.setCandidatePassword(request.getCandidatePassword()); // You should hash and salt the password

        return candidateRepository.save(candidate);

    }

    public List<Candidate> getAllCandidates(){
        return  candidateRepository.findAll();
    }

}
