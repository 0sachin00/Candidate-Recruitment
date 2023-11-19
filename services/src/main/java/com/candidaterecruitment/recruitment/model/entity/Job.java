package com.candidaterecruitment.recruitment.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "company_name", nullable = false)
    private String jobCompany;

    @Column(name = "description", nullable = false)
    private String jobDescription;

    @Column(name = "location", nullable = false)
    private String jobLocation;

    @Column(name = "min_experience")
    private float minJobExperience;

    @Column(name = "max_experience")
    private float maxJobExperience;

    @Column(name = "skills")
    private List<String> jobSkills;

    @Column(name = "type")
    private String jobType;

    @Column(name = "status")
    private List<String> jobStatus;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private Recruiter recruiter;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private Set<AppliedJob> appliedJobs = new HashSet<>();
}
