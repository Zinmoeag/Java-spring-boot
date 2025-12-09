package com.jobapi.jobapi.repositorires;

import com.jobapi.jobapi.domains.entities.JobEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJopRespository extends CrudRepository<JobEntity, Long> {

}
