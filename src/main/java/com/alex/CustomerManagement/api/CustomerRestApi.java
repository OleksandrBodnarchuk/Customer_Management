package com.alex.CustomerManagement.api;

import com.alex.CustomerManagement.domain.CustomerFacade;
import com.alex.CustomerManagement.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/api/v1/customers")
final class CustomerRestApi {

    private final CustomerFacade facade;

    CustomerRestApi(CustomerFacade facade) {
        this.facade = requireNonNull(facade);
    }

//    @GetMapping
//    List<CustomerDto> findAll() {
//        return facade.findAll();
//    }

    @PostMapping(params = { "type=person" })
    PersonDto createPerson(@RequestBody CreatePersonDto dto) {
        return facade.createPerson(dto);
    }

    @PostMapping(params = { "type=company" })
    CompanyDto createCompany(@RequestBody CreateCompanyDto dto) {
        return facade.createCompany(dto);
    }

    @GetMapping
    List<CustomerDto> filterPerson(PersonFilterDto dto) {
        return facade.filterPerson(dto);
    }
}
