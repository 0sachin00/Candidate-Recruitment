package com.candidaterecruitment.recruitment.repository;

import com.candidaterecruitment.recruitment.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, String> {
}
