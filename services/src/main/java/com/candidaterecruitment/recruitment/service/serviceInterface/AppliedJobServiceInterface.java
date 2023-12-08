package com.candidaterecruitment.recruitment.service.serviceInterface;

import com.candidaterecruitment.recruitment.model.dto.postrequests.JobRequest;
import com.candidaterecruitment.recruitment.model.entity.AppliedJob;
import com.candidaterecruitment.recruitment.model.entity.Job;

import java.util.List;

public interface AppliedJobServiceInterface {
    public List<AppliedJob> getAllAppliedJobs();
}
