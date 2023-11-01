package com.candidaterecruitment.recruitment.service.serviceImplementation;

import com.candidaterecruitment.recruitment.model.dto.requests.RecruiterRequest;
import com.candidaterecruitment.recruitment.model.entity.Recruiter;
import com.candidaterecruitment.recruitment.repository.RecruiterRepository;
import com.candidaterecruitment.recruitment.customexceptions.RecruiterRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterServiceImplementation {
    @Autowired
    public RecruiterRepository recruiterRepository;

    public Recruiter registerRecruiter(RecruiterRequest request){
        if (recruiterRepository.findByRecruiterEmail(request.getRecruiterEmail()).isPresent()) {
            throw new RecruiterRegistrationException("Email is already in use.");
        }

        Recruiter recruiter = new Recruiter();

        recruiter.setRecruiterName(request.getRecruiterName());
        recruiter.setRecruiterEmail(request.getRecruiterEmail());
        recruiter.setRecruiterPassword(request.getRecruiterPassword());

        return recruiterRepository.save(recruiter);
    }


    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }
}
