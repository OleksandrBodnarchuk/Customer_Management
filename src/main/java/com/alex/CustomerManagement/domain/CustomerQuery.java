package com.alex.CustomerManagement.domain;


import static com.alex.CustomerManagement.domain.CustomerSpec.withPersonFilter;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.alex.CustomerManagement.dto.CompanyDto;
import com.alex.CustomerManagement.dto.CustomerDto;
import com.alex.CustomerManagement.dto.PersonDto;
import com.alex.CustomerManagement.dto.PersonFilterDto;
import org.springframework.stereotype.Service;

@Service
final class CustomerQuery {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    CustomerQuery(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = requireNonNull(repository);
        this.mapper = requireNonNull(mapper);
    }

    List<CustomerDto> findAll() {
        return repository.findAll().stream()
            .map(mapper::map)
            .collect(toList());
    }

    List<CustomerDto> filterPerson(PersonFilterDto filterDto) {
        return repository.findAll(withPersonFilter(filterDto)).stream()
            .map(mapper::map)
            .collect(toList());
    }

    Optional<PersonDto> findPersonById(UUID id) {
        return repository.findPersonById(id).map(person -> new PersonDto(person.getId(),
            person.getFirstName(),
            person.getLastName(),
            person.getPesel()));
    }

    Optional<CompanyDto> findCompanyById(UUID id) {
        return repository.findCompanyById(id).map(company -> new CompanyDto(company.getId(),
            company.getName(),
            company.getVatNumber().getValue()));
    }
}
