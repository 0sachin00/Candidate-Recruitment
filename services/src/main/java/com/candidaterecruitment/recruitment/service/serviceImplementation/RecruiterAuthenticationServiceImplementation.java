package com.candidaterecruitment.recruitment.service.serviceImplementation;

import com.candidaterecruitment.recruitment.customexceptions.RecruiterRegistrationException;
import com.candidaterecruitment.recruitment.model.dto.getresponses.AuthenticationResponse;
import com.candidaterecruitment.recruitment.model.dto.postrequests.RecruiterAuthenticationRequest;
import com.candidaterecruitment.recruitment.model.dto.postrequests.RecruiterRequest;
import com.candidaterecruitment.recruitment.model.entity.Recruiter;
import com.candidaterecruitment.recruitment.repository.RecruiterRepository;
import com.candidaterecruitment.recruitment.service.serviceInterface.RecruiterAuthenticationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RecruiterAuthenticationServiceImplementation implements RecruiterAuthenticationServiceInterface {

    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtServiceImplementation jwtServiceImplementation;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse signUp(RecruiterRequest request) {
        Recruiter recruiter = Recruiter.builder()
                .recruiterName(request.getRecruiterName())
                .recruiterEmail(request.getRecruiterEmail())
                .recruiterPassword(request.getRecruiterPassword())
                .build();
        String rawPassword = recruiter.getPassword();
        if(rawPassword.length() < 6 || rawPassword.length()>20){
            throw new IllegalArgumentException("Password length must be between 6 and 20 characters");
        }
        recruiter.setRecruiterPassword(passwordEncoder.encode(rawPassword));
        Recruiter savedRecruiter = recruiterRepository.save(recruiter);
        String jwtToken = jwtServiceImplementation.generateToken(savedRecruiter);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse signIn(RecruiterAuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getRecruiterEmail(),
                        request.getRecruiterPassword()
                )
        );
        Recruiter recruiter = recruiterRepository.findByRecruiterEmail(request.getRecruiterEmail())
                .orElseThrow(() -> new RecruiterRegistrationException("Recruiter Not Found for "+request.getRecruiterEmail()+"."));
        String jwtToken = jwtServiceImplementation.generateToken(recruiter);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
