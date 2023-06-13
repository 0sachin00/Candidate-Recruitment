package com.candidaterecruitment.recruitment.repository;

import com.candidaterecruitment.recruitment.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}
