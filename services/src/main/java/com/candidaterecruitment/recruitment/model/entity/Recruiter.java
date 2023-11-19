package com.candidaterecruitment.recruitment.model.entity;

import com.candidaterecruitment.recruitment.idgenerator.IdentifiableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recruiters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruiter implements IdentifiableEntity {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.candidaterecruitment.recruitment.idgenerator.CustomIdGenerator")
    @Column(name = "id")
    private String recruiterId;

    @Column(name = "name", nullable = false)
    private String recruiterName;

    @Column(name = "email", nullable = false)
    private String recruiterEmail;

    @Column(name = "password", nullable = false)
    @Size(min = 6, max = 20)
    private String recruiterPassword;

    @OneToMany(mappedBy = "recruiter")
    private Set<Job> jobs = new HashSet<>();

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private Set<AppliedJob> appliedJobs = new HashSet<>();

    @Override
    public String getIdPrefix() {
        return "R";
    }
}
