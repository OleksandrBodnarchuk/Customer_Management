package com.alex.CustomerManagement.domain;

import com.alex.CustomerManagement.dto.CreateCompanyDto;
import com.alex.CustomerManagement.dto.CreatePersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    @Test
    @Transactional
    void testCreatePerson(){

        //given
        final var dto = new CreatePersonDto("Jan", "Go", "4652317823");

        //when
        final var person = service.createPerson(dto);

        //then
        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals("Jan", person.getFirstName());
        assertEquals("Go", person.getLastName());
        assertEquals("4652317823", person.getPesel());

    }

    @Test
    @Transactional
    void testCreateCompany(){
        //given
        final var dto = new CreateCompanyDto("Test S.A", "4564324654");

        //when
        final var company = service.createCompany(dto);

        //then
        assertNotNull(company);
        assertNotNull(company.getId());
        assertEquals("Test S.A", company.getName());
        assertEquals("4564324654", company.getVatNumber());

    }
}
