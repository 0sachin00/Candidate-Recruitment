package com.candidaterecruitment.recruitment.repository;

import com.candidaterecruitment.recruitment.model.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, String> {
    Optional<Recruiter> findByRecruiterEmail(String recruiterEmail);
}
