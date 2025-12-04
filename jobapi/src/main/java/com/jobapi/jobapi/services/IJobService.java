package com.jobapi.jobapi.services;

import com.jobapi.jobapi.domains.dtos.JobDTO;
import com.jobapi.jobapi.domains.entities.JobEntity;

public interface IJobService {
    public JobEntity create(JobEntity jobEntity);
}
