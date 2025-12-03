package com.jobapi.jobapi.repositorires;

import com.jobapi.jobapi.domains.entities.CompanyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyRespository extends CrudRepository<CompanyEntity, Long> {
}
