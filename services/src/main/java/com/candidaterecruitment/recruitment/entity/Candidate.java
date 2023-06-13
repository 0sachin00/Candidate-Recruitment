package com.candidaterecruitment.recruitment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "candidates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private String candidateId;

    @Column(name = "name")
    private String candidateName;

    @Column(name = "email", unique = true, nullable = false)
    private String candidateEmail;

    @Column(name = "password", nullable = false)
    private String candidatePassword;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private Set<AppliedJob> appliedJobs = new HashSet<AppliedJob>();
}
