package com.jobapi.jobapi.domains;

import com.jobapi.jobapi.domains.dtos.CompanyDTO;
import com.jobapi.jobapi.domains.entities.CompanyEntity;
import com.jobapi.jobapi.ulits.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements Mapper<CompanyEntity, CompanyDTO> {

    private final ModelMapper modelMapper;

    public CompanyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CompanyDTO mapTo(CompanyEntity companyEntity) {
        return modelMapper.map(companyEntity, CompanyDTO.class);
    }

    @Override
    public CompanyEntity mapFrom(CompanyDTO companyDTO) {
        return modelMapper.map(companyDTO, CompanyEntity.class);
    }
}
