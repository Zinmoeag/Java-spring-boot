package com.jobapi.jobapi.services;

import com.jobapi.jobapi.domains.dtos.JobDTO;
import com.jobapi.jobapi.domains.entities.JobEntity;
import com.jobapi.jobapi.repositorires.IJopRespository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        System.out.println(result + "result ===>");
        return result;
    }

    @Override
    public List<JobEntity> findAll() {
        return StreamSupport
                .stream(jobRespository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
