package com.alex.CustomerManagement;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PersonTest {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void testCreatePerson(){

        //  given
        final var person = new Person("Jan","Novak","135795167");

        //  when
        repository.save(person);
        em.flush();
        em.clear();
        // then
        final var readPerson = Hibernate.unproxy(repository.getOne(person.getId()), Customer.class);
        assertEquals(person, readPerson);

    }

}
