package com.candidaterecruitment.recruitment.model.dto.responses;

import com.candidaterecruitment.recruitment.model.dto.responseDetails.JobDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
    private List<JobDetails> res;
}
