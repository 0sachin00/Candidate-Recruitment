package com.candidaterecruitment.recruitment.repository;

import com.candidaterecruitment.recruitment.model.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CandidateRepository extends JpaRepository<Candidate, String> {
    Optional<Candidate> findByCandidateName(String candidateName);
    Optional<Candidate> findByCandidateEmail(String candidateEmail);
}
