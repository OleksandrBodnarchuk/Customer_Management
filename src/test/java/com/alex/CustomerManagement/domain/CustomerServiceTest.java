package com.alex.CustomerManagement.domain;

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
}
