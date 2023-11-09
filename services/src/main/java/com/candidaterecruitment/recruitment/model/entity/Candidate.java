package com.candidaterecruitment.recruitment.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "candidates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID candidateId;

    @Column(name = "name", nullable = false)
    private String candidateName;

    @Column(name = "email", unique = true, nullable = false)
    private String candidateEmail;


    @Column(name = "password", nullable = false)
    @Size(min = 6, max = 20)
    private String candidatePassword;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private Set<AppliedJob> appliedJobs = new HashSet<>();
}
