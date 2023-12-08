package com.candidaterecruitment.recruitment.service.serviceImplementation;

import com.candidaterecruitment.recruitment.customexceptions.CandidateRegistrationException;
import com.candidaterecruitment.recruitment.model.dto.getresponses.AuthenticationResponse;
import com.candidaterecruitment.recruitment.model.dto.postrequests.CandidateAuthenticationRequest;
import com.candidaterecruitment.recruitment.model.dto.postrequests.CandidateRequest;
import com.candidaterecruitment.recruitment.model.entity.Candidate;
import com.candidaterecruitment.recruitment.repository.CandidateRepository;
import com.candidaterecruitment.recruitment.service.serviceInterface.CandidateAuthenticationServiceInterface;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CandidateAuthenticationServiceImplementation implements CandidateAuthenticationServiceInterface {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtServiceImplementation jwtServiceImplementation;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse signUp(CandidateRequest request) {
        Candidate candidate = Candidate.builder()
                .candidateName(request.getCandidateName())
                .candidateEmail(request.getCandidateEmail())
                .candidatePassword(request.getCandidatePassword())
                .build();
        String rawPassword = candidate.getPassword();
        if(rawPassword.length() < 6 || rawPassword.length()>20){
            throw new IllegalArgumentException("Password length must be between 6 and 20 characters");
        }
        candidate.setCandidatePassword(passwordEncoder.encode(rawPassword));
        Candidate savedCandidate = candidateRepository.save(candidate);
        String jwtToken = jwtServiceImplementation.generateToken(savedCandidate);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse signIn(CandidateAuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCandidateEmail(),
                        request.getCandidatePassword()
                )
        );
        Candidate candidate = candidateRepository.findByCandidateEmail(request.getCandidateEmail())
                .orElseThrow(() -> new CandidateRegistrationException("Candidate Not Found for "+request.getCandidateEmail()+"."));
        String jwtToken = jwtServiceImplementation.generateToken(candidate);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
