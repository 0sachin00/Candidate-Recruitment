package com.candidaterecruitment.recruitment.model.dto.getresponses;

import com.candidaterecruitment.recruitment.model.dto.responsedetails.getresponsedetails.JobGetResponseDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
    private List<JobGetResponseDetails> res;
}
