package com.candidaterecruitment.recruitment.repository;

import com.candidaterecruitment.recruitment.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, String> {
}
