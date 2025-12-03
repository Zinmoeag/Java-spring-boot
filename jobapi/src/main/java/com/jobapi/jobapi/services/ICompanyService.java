package com.jobapi.jobapi.services;

import com.jobapi.jobapi.domains.entities.CompanyEntity;

import java.util.List;

public interface ICompanyService {
    CompanyEntity save(CompanyEntity company);
    List<CompanyEntity> findAll();
}
