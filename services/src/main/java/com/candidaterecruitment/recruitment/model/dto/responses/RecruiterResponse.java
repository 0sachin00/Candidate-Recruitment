package com.candidaterecruitment.recruitment.model.dto.responses;

import com.candidaterecruitment.recruitment.model.dto.responseDetails.RecruiterDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruiterResponse {
    private List<RecruiterDetails> res;
}
