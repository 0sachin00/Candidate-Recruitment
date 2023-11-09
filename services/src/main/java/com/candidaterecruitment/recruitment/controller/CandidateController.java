package com.candidaterecruitment.recruitment.controller;

import com.candidaterecruitment.recruitment.model.dto.responseDetails.CandidateDetails;
import com.candidaterecruitment.recruitment.model.dto.requests.CandidateRequest;
import com.candidaterecruitment.recruitment.model.dto.responseDetails.CandidateRegistrationDetails;
import com.candidaterecruitment.recruitment.model.dto.responses.CandidateResponse;
import com.candidaterecruitment.recruitment.model.entity.Candidate;
import com.candidaterecruitment.recruitment.service.serviceImplementation.CandidateServiceImplementation;
import com.candidaterecruitment.recruitment.customexceptions.CandidateRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @PostMapping("/registerCandidate")
    public ResponseEntity<CandidateRegistrationDetails> registerCandidate(@RequestBody CandidateRequest request) {
        try{
            Candidate registeredCandidate = candidateServiceImplementation.registerCandidate(request);
            CandidateRegistrationDetails response = new CandidateRegistrationDetails();
            response.setMessage("Candidate registered successfully. Candidate ID: " + registeredCandidate.getCandidateId());
            response.setStatusCode(String.valueOf(200));
            return ResponseEntity.ok(response);
        }catch (CandidateRegistrationException e){
            CandidateRegistrationDetails response = new CandidateRegistrationDetails();
            response.setMessage(e.getMessage());
            response.setStatusCode(String.valueOf(409));
            return ResponseEntity.ok(response);
        }
    }
}
