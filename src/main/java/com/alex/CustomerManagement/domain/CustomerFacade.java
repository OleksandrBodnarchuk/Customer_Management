package com.alex.CustomerManagement.domain;

import com.alex.CustomerManagement.dto.CreatePersonDto;
import com.alex.CustomerManagement.dto.CustomerDto;
import com.alex.CustomerManagement.dto.PersonDto;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
