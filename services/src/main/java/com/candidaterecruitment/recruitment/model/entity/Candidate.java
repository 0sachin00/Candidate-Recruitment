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
@Table(name = "candidates")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candidate implements IdentifiableEntity ,UserDetails{
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
    private String candidatePassword;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private Set<AppliedJob> appliedJobs = new HashSet<>();

    @Override
    public String getIdPrefix() {
        return "C";
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return candidatePassword;
    }

    @Override
    public String getUsername() {
        return candidateEmail;
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
