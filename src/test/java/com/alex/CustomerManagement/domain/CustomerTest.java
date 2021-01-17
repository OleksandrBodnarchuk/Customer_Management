package com.alex.CustomerManagement.domain;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerTest extends EntityTest {

    @Test
    @Transactional
    void testCreatePerson() {
        // given
        final var person = new Person("Jan", "Nowak", "90282939393");

        // when
        save(person);

        // then
        assertEquals(person, readCustomer(person.getId()));
    }

    @Test
    @Transactional
    void testCreateCompany() {
        // given
        final var company = new Company("Test S.A.", "938493993");

        // when
        save(company);

        // then
        assertEquals(company, readCustomer(company.getId()));
    }

    @Test
    @Transactional
    void testAddAddress() {
        // given
        final var customer = new Company("Test S.A.", "929030202");
        final var address = new Address("str", "wawa", "01-200", "PL");
        customer.addAddress(address);

        // when
        save(customer);

        // then
        final var readCustomer = readCustomer(customer.getId());
        assertEquals(1, readCustomer.getAddresses().size());
        assertTrue(readCustomer.getAddresses().contains(address));
    }
}