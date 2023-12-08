package com.candidaterecruitment.recruitment.model.entity;

import com.candidaterecruitment.recruitment.idgenerator.IdentifiableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recruiters")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recruiter implements IdentifiableEntity, UserDetails {
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
    private String recruiterPassword;

    @OneToMany(mappedBy = "recruiter")
    private Set<Job> jobs = new HashSet<>();

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private Set<AppliedJob> appliedJobs = new HashSet<>();

    @Override
    public String getIdPrefix() {
        return "R";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return recruiterPassword;
    }

    @Override
    public String getUsername() {
        return recruiterEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
