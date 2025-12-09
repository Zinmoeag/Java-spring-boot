package com.jobapi.jobapi.services;

import com.jobapi.jobapi.domains.dtos.JobDTO;
import com.jobapi.jobapi.domains.entities.JobEntity;

import java.util.List;

public interface IJobService {
    public JobEntity create(JobEntity jobEntity);

    List<JobEntity> findAll();

    JobEntity findById(Long id);

    boolean isExist(Long jobId);

    JobEntity update(Long id, JobEntity jobEntity) throws Exception;

    JobEntity partialUpdate(Long id, JobEntity jobEntity) throws Exception;

    void delete(Long id) throws Exception;
}
