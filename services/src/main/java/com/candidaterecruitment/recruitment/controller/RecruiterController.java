package com.candidaterecruitment.recruitment.controller;

import com.candidaterecruitment.recruitment.model.dto.postrequests.RecruiterRequest;
import com.candidaterecruitment.recruitment.model.dto.responsedetails.getresponsedetails.RecruiterGetResponseDetails;
import com.candidaterecruitment.recruitment.model.dto.getresponses.RecruiterResponse;
import com.candidaterecruitment.recruitment.model.dto.responsedetails.postresponsedetails.RecruiterPostResponseDetails;
import com.candidaterecruitment.recruitment.model.entity.Recruiter;
import com.candidaterecruitment.recruitment.service.serviceImplementation.RecruiterServiceImplementation;
import com.candidaterecruitment.recruitment.customexceptions.RecruiterRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    @Autowired
    public RecruiterServiceImplementation recruiterServiceImplementation;

    @GetMapping("/allRecruiters")
    public ResponseEntity<RecruiterResponse> getRecruiterDetails(){
        List<Recruiter> recruiters = recruiterServiceImplementation.getAllRecruiters();
        List<RecruiterGetResponseDetails> responseList = recruiters.stream()
                .map(recruiter -> {
                    RecruiterGetResponseDetails response = new RecruiterGetResponseDetails();
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
    public ResponseEntity<RecruiterPostResponseDetails> registerRecruiter(@RequestBody RecruiterRequest request) {
        try{
            Recruiter registeredRecruiter = recruiterServiceImplementation.registerRecruiter(request);
            RecruiterPostResponseDetails recruiterPostResponseDetails = new RecruiterPostResponseDetails();
            recruiterPostResponseDetails.setMessage("Recruiter registered successfully. Recruiter ID: " + registeredRecruiter.getRecruiterId());
            recruiterPostResponseDetails.setStatusCode(String.valueOf(200));
            return ResponseEntity.ok(recruiterPostResponseDetails);
        }catch (RecruiterRegistrationException e){
            RecruiterPostResponseDetails recruiterPostResponseDetails = new RecruiterPostResponseDetails();
            recruiterPostResponseDetails.setMessage(e.getMessage());
            recruiterPostResponseDetails.setStatusCode(String.valueOf(409));
            return ResponseEntity.ok(recruiterPostResponseDetails);
        }
    }
}
