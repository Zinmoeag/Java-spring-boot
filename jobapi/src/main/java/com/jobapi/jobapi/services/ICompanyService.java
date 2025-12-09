package com.jobapi.jobapi.services;

import com.jobapi.jobapi.domains.entities.CompanyEntity;

import java.util.List;

public interface ICompanyService {
    CompanyEntity save(CompanyEntity company);
    List<CompanyEntity> findAll();

    CompanyEntity findById(Long id);

    boolean isExist(Long id);

    CompanyEntity update(Long id, CompanyEntity company) throws Exception;

    CompanyEntity partialUpdate(Long id, CompanyEntity company) throws Exception;

    void delete(Long id) throws Exception;
}
