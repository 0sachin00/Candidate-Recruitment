package com.candidaterecruitment.recruitment.service.serviceInterface;

import com.candidaterecruitment.recruitment.model.dto.getresponses.AuthenticationResponse;
import com.candidaterecruitment.recruitment.model.dto.postrequests.RecruiterAuthenticationRequest;
import com.candidaterecruitment.recruitment.model.dto.postrequests.RecruiterRequest;

public interface RecruiterAuthenticationServiceInterface {
    public AuthenticationResponse signUp(RecruiterRequest request);
    public AuthenticationResponse signIn(RecruiterAuthenticationRequest request);
}
