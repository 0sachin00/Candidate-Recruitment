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
@Table(name = "candidates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate implements IdentifiableEntity {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.candidaterecruitment.recruitment.idgenerator.CustomIdGenerator")
    @Column(name = "id")
    private String candidateId;

    @Column(name = "name", nullable = false)
    private String candidateName;

    @Column(name = "email", unique = true, nullable = false)
    private String candidateEmail;


    @Column(name = "password", nullable = false)
    @Size(min = 6, max = 20)
    private String candidatePassword;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private Set<AppliedJob> appliedJobs = new HashSet<>();

    @Override
    public String getIdPrefix() {
        return "C";
    }

}
