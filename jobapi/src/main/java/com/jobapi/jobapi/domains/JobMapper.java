package com.jobapi.jobapi.domains;

import com.jobapi.jobapi.domains.dtos.JobDTO;
import com.jobapi.jobapi.domains.entities.JobEntity;
import com.jobapi.jobapi.ulits.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JobMapper implements Mapper<JobEntity, JobDTO> {

    private final ModelMapper modelMapper;

    public JobMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public JobDTO mapTo(JobEntity jobEntity) {
        return modelMapper.map(jobEntity, JobDTO.class);
    }

    @Override
    public JobEntity mapFrom(JobDTO jobDTO) {
        return modelMapper.map(jobDTO, JobEntity.class);
    }
}
