package com.alex.CustomerManagement.domain;



import java.util.List;

import com.alex.CustomerManagement.dto.*;
import org.springframework.stereotype.Service;

@Service
public class CustomerFacade {

    private final CustomerService service;
    private final CustomerQuery query;

    CustomerFacade(CustomerService service, CustomerQuery query) {
        this.service = service;
        this.query = query;
    }

    public List<CustomerDto> findAll() {
        return query.findAll();
    }

    public PersonDto createPerson(CreatePersonDto dto) {
        return service.createPerson(dto);
    }


    public CompanyDto createCompany(CreateCompanyDto dto) {
        return service.createCompany(dto);
    }

    public List<CustomerDto> filterPerson(PersonFilterDto dto) {
        return query.filterPerson(dto);
    }
}
