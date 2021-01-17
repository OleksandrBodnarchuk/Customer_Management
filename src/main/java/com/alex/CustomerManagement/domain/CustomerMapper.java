package com.alex.CustomerManagement.domain;


import com.alex.CustomerManagement.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
final class CustomerMapper {

    CustomerDto map(Customer customer) {
        if (customer instanceof Company) {
            final var company = (Company) customer;
            return new CustomerDto(company.getId(),
                CustomerDto.Type.COMPANY,
                company.getName(),
                company.getVatNumber().getValue());
        } else if (customer instanceof Person) {
            final var person = (Person) customer;
            return new CustomerDto(person.getId(),
                CustomerDto.Type.PERSON,
                person.getFullName(),
                person.getPesel());
        }
        throw new IllegalStateException("customer mapping not found for class: " + customer.getClass().getName());
    }
}
