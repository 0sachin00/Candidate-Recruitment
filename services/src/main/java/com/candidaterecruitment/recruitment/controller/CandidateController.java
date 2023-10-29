package com.candidaterecruitment.recruitment.controller;

import com.candidaterecruitment.recruitment.model.dto.CandidateDetails;
import com.candidaterecruitment.recruitment.model.dto.CandidateRegistrationRequest;
import com.candidaterecruitment.recruitment.model.dto.CandidateResponse;
import com.candidaterecruitment.recruitment.model.entity.Candidate;
import com.candidaterecruitment.recruitment.service.serviceImplementation.CandidateServiceImplementation;
import customexceptions.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    @Autowired
    public CandidateServiceImplementation candidateServiceImplementation;

    @GetMapping("/allCandidates")
    public ResponseEntity<CandidateResponse> getCandidateDetails(){
        List<Candidate> candidates = candidateServiceImplementation.getAllCandidates();
        List<CandidateDetails> responseList = candidates.stream()
                .map(candidate -> {
                    CandidateDetails response = new CandidateDetails();
                    response.setId(candidate.getCandidateId());
                    response.setCandidateName(candidate.getCandidateName());
                    response.setCandidateEmail(candidate.getCandidateEmail());
                    return response;
                })
                .collect(Collectors.toList());
        CandidateResponse response = new CandidateResponse();
        response.setRes(responseList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCandidate(@RequestBody CandidateRegistrationRequest request) {
        try{
            Candidate registeredCandidate = candidateServiceImplementation.registerCandidate(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Candidate registered successfully. Candidate ID: " + registeredCandidate.getCandidateId());
        }catch (RegistrationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
