package com.jobapi.jobapi.controllers;

import com.jobapi.jobapi.domains.CompanyMapper;
import com.jobapi.jobapi.domains.JobMapper;
import com.jobapi.jobapi.domains.dtos.JobDTO;
import com.jobapi.jobapi.domains.entities.JobEntity;
import com.jobapi.jobapi.services.IJobService;
import com.jobapi.jobapi.services.JobService;
import org.hibernate.mapping.Any;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(path = "/jobs")
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        List<JobEntity> results = jobService.findAll();
        List<JobDTO> jobDTOS = results.stream().map(jobMapper::mapTo).collect(Collectors.toList());
        return new ResponseEntity<>(jobDTOS, HttpStatus.OK);
    }

    @GetMapping(path = "/jobs/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        JobDTO resuls = jobMapper.mapTo(jobService.findById(id));
        return new ResponseEntity<JobDTO>(resuls, HttpStatus.OK);
    }

    @PostMapping(path = "/jobs/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable Long id, @RequestBody JobDTO jobDTO) {
        if(!jobService.isExist(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        jobDTO.setId(id);
        JobEntity jobEntity = jobMapper.mapFrom(jobDTO);
        JobEntity createJob = jobService.create(jobEntity);
        return new ResponseEntity<JobDTO>(jobMapper.mapTo(createJob), HttpStatus.OK);
    }

    @PatchMapping(path = "/jobs/{id}")
    public ResponseEntity<JobDTO> partialUpdateJob(@PathVariable Long id, @RequestBody JobDTO jobDTO) throws Exception {
        if(!jobService.isExist(id)) return new ResponseEntity(HttpStatus.NOT_FOUND);
        jobDTO.setId(id);
        JobEntity jobEntity = jobMapper.mapFrom(jobDTO);
        return new ResponseEntity<>(
                jobMapper.mapTo(jobService.partialUpdate(id, jobEntity))
                , HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/jobs/{id}")
    public ResponseEntity<Any> deleteJob(@PathVariable Long id) throws Exception {
        jobService.delete(id);
        return new ResponseEntity("success", HttpStatus.NO_CONTENT);
    }
}
