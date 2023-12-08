package com.candidaterecruitment.recruitment.service.serviceInterface;

import com.candidaterecruitment.recruitment.model.dto.getresponses.AuthenticationResponse;
import com.candidaterecruitment.recruitment.model.dto.postrequests.CandidateAuthenticationRequest;
import com.candidaterecruitment.recruitment.model.dto.postrequests.CandidateRequest;

public interface CandidateAuthenticationServiceInterface {
    public AuthenticationResponse signUp (CandidateRequest request);
    public AuthenticationResponse signIn (CandidateAuthenticationRequest request);
}
