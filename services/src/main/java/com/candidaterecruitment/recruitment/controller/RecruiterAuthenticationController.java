package com.candidaterecruitment.recruitment.controller;

import com.candidaterecruitment.recruitment.model.dto.getresponses.AuthenticationResponse;
import com.candidaterecruitment.recruitment.model.dto.postrequests.RecruiterAuthenticationRequest;
import com.candidaterecruitment.recruitment.model.dto.postrequests.RecruiterRequest;
import com.candidaterecruitment.recruitment.service.serviceImplementation.RecruiterAuthenticationServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/recruiter")
public class RecruiterAuthenticationController {

    @Autowired
    private RecruiterAuthenticationServiceImplementation recruiterAuthenticationServiceImplementation;

    @PostMapping("/signup")
    private ResponseEntity<AuthenticationResponse> signUp (@RequestBody RecruiterRequest request){
        return ResponseEntity.ok(recruiterAuthenticationServiceImplementation.signUp(request));
    }

    @PostMapping("/signin")
    private ResponseEntity<AuthenticationResponse> signIn (@RequestBody RecruiterAuthenticationRequest request){
        return ResponseEntity.ok(recruiterAuthenticationServiceImplementation.signIn(request));
    }
}
