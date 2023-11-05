package com.candidaterecruitment.recruitment.controller;

import com.candidaterecruitment.recruitment.model.dto.requests.RecruiterRequest;
import com.candidaterecruitment.recruitment.model.dto.responseDetails.RecruiterDetails;
import com.candidaterecruitment.recruitment.model.dto.responses.RecruiterResponse;
import com.candidaterecruitment.recruitment.model.entity.Recruiter;
import com.candidaterecruitment.recruitment.service.serviceImplementation.RecruiterServiceImplementation;
import com.candidaterecruitment.recruitment.customexceptions.RecruiterRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    @Autowired
    public RecruiterServiceImplementation recruiterServiceImplementation;

    @GetMapping("/allRecruiters")
    public ResponseEntity<RecruiterResponse> getRecruiterDetails(){
        List<Recruiter> recruiters = recruiterServiceImplementation.getAllRecruiters();
        List<RecruiterDetails> responseList = recruiters.stream()
                .map(recruiter -> {
                    RecruiterDetails response = new RecruiterDetails();
                    response.setId(recruiter.getRecruiterId());
                    response.setRecruiterName(recruiter.getRecruiterName());
                    response.setRecruiterEmail(recruiter.getRecruiterEmail());
                    return response;
                })
                .collect(Collectors.toList());
        RecruiterResponse response = new RecruiterResponse();
        response.setRes(responseList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registerRecruiter")
    public ResponseEntity<String> registerRecruiter(@RequestBody RecruiterRequest request) {
        try{
            Recruiter registeredRecruiter = recruiterServiceImplementation.registerRecruiter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Recruiter registered successfully. Recruiter ID: " + registeredRecruiter.getRecruiterId());
        }catch (RecruiterRegistrationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
