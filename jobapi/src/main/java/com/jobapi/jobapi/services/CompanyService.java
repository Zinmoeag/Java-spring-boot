package com.jobapi.jobapi.services;

import com.jobapi.jobapi.domains.entities.CompanyEntity;
import com.jobapi.jobapi.repositorires.ICompanyRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyService implements ICompanyService {

    private final ICompanyRespository companyRespository;

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

    @Override
    public CompanyEntity findById(Long id) {
        return companyRespository.findById(id).get();
    }

    @Override
    public boolean isExist(Long id) {
        return companyRespository.existsById(id);
    }

    @Override
    public CompanyEntity update(Long id, CompanyEntity company) throws Exception {
        if(!isExist(company.getId())) throw new Exception("it is already exist");
        return companyRespository.save(company);
    }

    @Override
    public CompanyEntity partialUpdate(Long id, CompanyEntity company) throws Exception {
        company.setId(id);

        return companyRespository.findById(id).map(existingCompany -> {
            Optional.ofNullable(company.getName()).ifPresent(existingCompany::setName);
            Optional.ofNullable(company.getEmail()).ifPresent(existingCompany::setEmail);
            Optional.ofNullable(company.getPhone()).ifPresent(existingCompany::setPhone);
            return companyRespository.save(existingCompany);
        }).orElseThrow(() -> new RuntimeException("Company does not exist"));
    }

    @Override
    public void delete(Long id) throws Exception {
        if(isExist(id)) companyRespository.deleteById(id);
    }
}
