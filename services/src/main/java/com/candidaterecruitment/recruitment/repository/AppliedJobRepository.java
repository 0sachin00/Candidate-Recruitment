package com.candidaterecruitment.recruitment.repository;

import com.candidaterecruitment.recruitment.entity.AppliedJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppliedJobRepository extends JpaRepository<AppliedJob, Long> {
}
