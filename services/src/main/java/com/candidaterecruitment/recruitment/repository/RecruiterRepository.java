package com.candidaterecruitment.recruitment.repository;

import com.candidaterecruitment.recruitment.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, String> {
}
