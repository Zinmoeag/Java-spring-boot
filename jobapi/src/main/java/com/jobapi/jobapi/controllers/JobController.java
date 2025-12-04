package com.jobapi.jobapi.controllers;

import com.jobapi.jobapi.domains.CompanyMapper;
import com.jobapi.jobapi.domains.JobMapper;
import com.jobapi.jobapi.domains.dtos.JobDTO;
import com.jobapi.jobapi.domains.entities.JobEntity;
import com.jobapi.jobapi.services.IJobService;
import com.jobapi.jobapi.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class JobController {
    private final JobMapper jobMapper;
    private final IJobService jobService;

    public JobController(JobMapper jobMapper, IJobService jobService) {
        this.jobMapper = jobMapper;
        this.jobService = jobService;
    }

    @PostMapping(path = "/jobs")
    public ResponseEntity<JobDTO> addJob(@RequestBody JobDTO jobDTO) {
        JobEntity jobEntity = jobMapper.mapFrom(jobDTO);
        JobEntity result = jobService.create(jobEntity);
        JobDTO responseDTO = jobMapper.mapTo(result);
        return new ResponseEntity<JobDTO>(responseDTO, HttpStatus.CREATED);
    }
}
