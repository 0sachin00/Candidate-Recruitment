package com.candidaterecruitment.recruitment.controller;

import com.candidaterecruitment.recruitment.model.dto.responsedetails.getresponsedetails.CandidateGetResponseDetails;
import com.candidaterecruitment.recruitment.model.dto.postrequests.CandidateRequest;
import com.candidaterecruitment.recruitment.model.dto.responsedetails.postresponsedetails.CandidatePostResponseDetails;
import com.candidaterecruitment.recruitment.model.dto.getresponses.CandidateResponse;
import com.candidaterecruitment.recruitment.model.entity.Candidate;
import com.candidaterecruitment.recruitment.service.serviceImplementation.CandidateServiceImplementation;
import com.candidaterecruitment.recruitment.customexceptions.CandidateRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<CandidateGetResponseDetails> responseList = candidates.stream()
                .map(candidate -> {
                    CandidateGetResponseDetails response = new CandidateGetResponseDetails();
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
    public ResponseEntity<CandidatePostResponseDetails> registerCandidate(@RequestBody CandidateRequest request) {
        try{
            Candidate registeredCandidate = candidateServiceImplementation.registerCandidate(request);
            CandidatePostResponseDetails response = new CandidatePostResponseDetails();
            response.setMessage("Candidate registered successfully. Candidate ID: " + registeredCandidate.getCandidateId());
            response.setStatusCode(String.valueOf(200));
            return ResponseEntity.ok(response);
        }catch (CandidateRegistrationException e){
            CandidatePostResponseDetails response = new CandidatePostResponseDetails();
            response.setMessage(e.getMessage());
            response.setStatusCode(String.valueOf(409));
            return ResponseEntity.ok(response);
        }
    }
}
