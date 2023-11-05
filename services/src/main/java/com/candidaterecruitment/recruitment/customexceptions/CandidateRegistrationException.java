package com.candidaterecruitment.recruitment.customexceptions;

public class CandidateRegistrationException extends RuntimeException {
    public CandidateRegistrationException(String message) {
        super(message);
    }
}