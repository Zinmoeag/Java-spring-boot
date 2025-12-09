package com.jobapi.jobapi.services;

import com.jobapi.jobapi.domains.dtos.JobDTO;
import com.jobapi.jobapi.domains.entities.JobEntity;
import com.jobapi.jobapi.repositorires.IJopRespository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JobService implements IJobService {

    private final IJopRespository jobRespository;

    public JobService(IJopRespository jobRespository) {
        this.jobRespository = jobRespository;
    }

    @Override
    public JobEntity create(JobEntity jobEntity) {
        JobEntity result = jobRespository.save(jobEntity);
        return result;
    }

    @Override
    public List<JobEntity> findAll() {
        return StreamSupport
                .stream(jobRespository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public JobEntity findById(Long id) {
        return jobRespository.findById(id).get();
    }

    @Override
    public boolean isExist(Long id) {
        return jobRespository.existsById(id);
    }

    @Override
    public JobEntity update(Long id, JobEntity jobEntity) throws Exception {
        if(!isExist(id)) throw new Exception("it is already exist");
        return jobRespository.save(jobEntity);
    }

    @Override
    public JobEntity partialUpdate(Long id, JobEntity jobEntity) throws Exception {
        return jobRespository.findById(id).map(existedJob -> {
            Optional.ofNullable(jobEntity.getTitle()).ifPresent(existedJob::setTitle);
            Optional.ofNullable(jobEntity.getEmail()).ifPresent(existedJob::setEmail);
            Optional.ofNullable(jobEntity.getCompany()).ifPresent(existedJob::setCompany);
            Optional.ofNullable(jobEntity.getDescription()).ifPresent(existedJob::setDescription);
            return jobRespository.save(existedJob);
        }).orElseThrow(() ->  new RuntimeException("Job is not exist"));
    }

    public void delete(Long id) {
        jobRespository.deleteById(id);
    }
}
