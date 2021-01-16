package com.alex.CustomerManagement;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CustomerTest {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void testCreatePerson() {

        //  given
        final var person = new Person("Jan", "Novak", "135795167");

        //  when
        save(person);
        // then
        assertEquals(person, readCustomer(person.getId()));

    }

    @Test
    @Transactional
    void testCreateCompany() {

        //given
        final var company = new Company("Test S.A", "45643463");

        //when
        save(company);

        //then
        assertEquals(company, readCustomer(company.getId()));

    }

    @Test
    @Transactional
    void testAddAddress(){

        //given
        final var customer = new Company("Test S.A", "546546463");
        final var address = new Address("str","Warsaw","77-100","PL");
        customer.addAddress(address);

        // when
        save(customer);

        //then
        final var readCustomer = readCustomer(customer.getId());
        assertEquals(1, readCustomer.getAddresses().size());
        assertTrue(readCustomer.getAddresses().contains(address));

    }


    private Customer readCustomer(UUID id) {
        return Hibernate.unproxy(repository.getOne(id), Customer.class);
    }

    private void save(Customer customer) {
        repository.save(customer);
        clearCache();
    }

    private void clearCache() {
        em.flush();
        em.clear();
    }

}
