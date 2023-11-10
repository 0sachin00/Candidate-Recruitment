package com.candidaterecruitment.recruitment.model.dto.getresponses;

import com.candidaterecruitment.recruitment.model.dto.responsedetails.getresponsedetails.CandidateGetResponseDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponse {

    private List<CandidateGetResponseDetails> res;
}
