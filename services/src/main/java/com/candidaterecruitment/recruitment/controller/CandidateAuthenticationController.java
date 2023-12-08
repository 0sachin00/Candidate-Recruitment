package com.candidaterecruitment.recruitment.controller;

import com.candidaterecruitment.recruitment.model.dto.getresponses.AuthenticationResponse;
import com.candidaterecruitment.recruitment.model.dto.postrequests.CandidateAuthenticationRequest;
import com.candidaterecruitment.recruitment.model.dto.postrequests.CandidateRequest;
import com.candidaterecruitment.recruitment.service.serviceImplementation.CandidateAuthenticationServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/candidate")
public class CandidateAuthenticationController {

    @Autowired
    private CandidateAuthenticationServiceImplementation candidateAuthenticationServiceImplementation;

    @PostMapping("/signup")
    private ResponseEntity<AuthenticationResponse> signUp (@RequestBody CandidateRequest request){
        return ResponseEntity.ok(candidateAuthenticationServiceImplementation.signUp(request));
    }

    @PostMapping("/signin")
    private ResponseEntity<AuthenticationResponse> signIn (@RequestBody CandidateAuthenticationRequest request){
        return ResponseEntity.ok(candidateAuthenticationServiceImplementation.signIn(request));
    }
}
