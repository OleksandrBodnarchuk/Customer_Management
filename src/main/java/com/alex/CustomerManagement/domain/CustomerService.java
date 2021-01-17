package com.alex.CustomerManagement.domain;

import com.alex.CustomerManagement.dto.CompanyDto;
import com.alex.CustomerManagement.dto.CreateCompanyDto;
import com.alex.CustomerManagement.dto.CreatePersonDto;
import com.alex.CustomerManagement.dto.PersonDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Objects.requireNonNull;

@Service
class CustomerService {


    private final CustomerRepository repository;

    CustomerService(CustomerRepository repository) {
        this.repository = requireNonNull(repository);

    }

    @Transactional
    PersonDto createPerson(CreatePersonDto dto) {
        final var person = new Person(dto.getFirstName(), dto.getLastName(), dto.getPesel());
        repository.save(person);
        return new PersonDto(person.getId(), person.getFirstName(), person.getLastName(), person.getPesel());
    }

    CompanyDto createCompany(CreateCompanyDto dto) {
        final var company = new Company(dto.getName(), dto.getVatNumber());
        repository.save(company);
        return new CompanyDto(company.getId(), company.getName(), company.getVatNumber());
    }
}