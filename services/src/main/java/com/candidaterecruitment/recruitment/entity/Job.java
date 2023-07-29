package com.candidaterecruitment.recruitment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String jobId;

    @Column(name = "company_name", nullable = false)
    private String jobCompany;

    @Column(name = "description", nullable = false)
    private String jobDescription;

    @Column(name = "location", nullable = false)
    private List<String> jobLocation;

    @Column(name = "experience")
    private int jobExperience;

    @Column(name = "skills")
    private List<String> jobSkills;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private Set<AppliedJob> appliedJobs = new HashSet<AppliedJob>();
}
