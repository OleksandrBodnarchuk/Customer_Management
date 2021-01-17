package com.alex.CustomerManagement.api;

import com.alex.CustomerManagement.domain.CustomerFacade;
import com.alex.CustomerManagement.dto.CustomerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/api/v1/customers")
final class CustomerRestApi {

    private final CustomerFacade facade;

    CustomerRestApi(CustomerFacade facade) {
        this.facade = requireNonNull(facade);
    }

    @GetMapping
    List<CustomerDto> findAll() {
        return facade.findAll();
    }
}
