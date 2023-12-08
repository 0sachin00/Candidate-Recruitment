package com.candidaterecruitment.recruitment.model.dto.getresponses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
}
