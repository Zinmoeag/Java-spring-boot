package com.jobapi.jobapi.services;

import com.jobapi.jobapi.domains.entities.CompanyEntity;
import com.jobapi.jobapi.repositorires.ICompanyRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyService implements ICompanyService {

    private ICompanyRespository companyRespository;

    public CompanyService(ICompanyRespository companyRespository) {
        this.companyRespository = companyRespository;
    }

    @Override
    public CompanyEntity save(CompanyEntity company) {
        return companyRespository.save(company);
    }

    @Override
    public List<CompanyEntity> findAll() {
        Iterable<CompanyEntity> results = companyRespository.findAll();
        return StreamSupport.stream(results.spliterator(), false).collect(Collectors.toList());
    }
}
