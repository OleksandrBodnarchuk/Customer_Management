package com.alex.CustomerManagement.domain;

import static com.alex.CustomerManagement.domain.CustomerSpec.withPersonFilter;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import javax.transaction.Transactional;

import com.alex.CustomerManagement.dto.PersonFilterDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerRepositoryTest extends EntityTest {

    @Test
    @Transactional
    void testFindCustomersByCity() {
        // given
        final var customer1 = new Person("Jan", "Nowak", "9282992992");
        customer1.addAddress(new Address("str1", "Warszawa", "01-300", "PL"));
        final var customer2 = new Company("Test S.A.", new VatNumber("9288383831"));
        customer2.addAddress(new Address("str2", "Kraków", "01-400", "PL"));
        final var customer3 = new Company("Testuj S.A.", new VatNumber("8290202020"));
        customer3.addAddress(new Address("str3", "Warszawa", "02-340", "PL"));
        saveAll(customer1, customer2, customer3);

        // when
        final var customers = repository.findByCity("warszawa");

        // then
        assertEquals(2, customers.size());
        assertTrue(customers.containsAll(asList(customer1, customer3)));
    }

    @Test
    @Transactional
    void testCountByCity() {
        // given
        final var customer1 = new Person("Jan", "Nowak", "9282992992");
        customer1.addAddress(new Address("str1", "Warszawa", "01-300", "PL"));
        final var customer2 = new Company("Test S.A.", new VatNumber("9288383831"));
        customer2.addAddress(new Address("str2", "Kraków", "01-400", "PL"));
        final var customer3 = new Company("Testuj S.A.", new VatNumber("8290202020"));
        customer3.addAddress(new Address("str3", "Warszawa", "02-340", "PL"));
        saveAll(customer1, customer2, customer3);

        // when
        final var results = repository.countByCity();

        // then
        assertEquals(2, results.size());
        assertArrayEquals(new Object[] { BigInteger.valueOf(1), "Kraków"}, results.get(0));
        assertArrayEquals(new Object[] { BigInteger.valueOf(2), "Warszawa" }, results.get(1));
    }

    @Test
    @Transactional
    void testCountByCityWithType() {
        // given
        final var customer1 = new Person("Jan", "Nowak", "9282992992");
        customer1.addAddress(new Address("str1", "Warszawa", "01-300", "PL"));
        final var customer2 = new Company("Test S.A.", new VatNumber("9288383831"));
        customer2.addAddress(new Address("str2", "Kraków", "01-400", "PL"));
        final var customer3 = new Company("Testuj S.A.", new VatNumber("8290202020"));
        customer3.addAddress(new Address("str3", "Warszawa", "02-340", "PL"));
        saveAll(customer1, customer2, customer3);

        // when
        final var results = repository.countByCityWithType();

        // then
        assertEquals(2, results.size());
        final var row1 = results.get(0);
        assertEquals("Kraków", row1.getCity());
        assertEquals(1, row1.getCount());
        final var row2 = results.get(1);
        assertEquals("Warszawa", row2.getCity());
        assertEquals(2, row2.getCount());
    }

    @Test
    @Transactional
    void testUpdatePersonName() {
        // given
        final var person = new Person("Jan", "Nowak", "839939393");
        saveAll(person);

        // when
        final var updated = repository.updatePersonName(person.getId(), "Janek", "Nowaky");

        // then
        assertEquals(1, updated);
        final var readPerson = (Person) readCustomer(person.getId());
        assertEquals("Janek", readPerson.getFirstName());
        assertEquals("Nowaky", readPerson.getLastName());
    }

    @Test
    @Transactional
    void testFilterPerson() {
        // given
        final var customer1 = new Person("Jan", "Nowak", "9282992992");
        final var customer2 = new Company("Test S.A.", new VatNumber("9288383831"));
        final var customer3 = new Company("Testuj S.A.", new VatNumber("8290202020"));
        final var customer4 = new Person("Adam", "Kowalski", "9283002020");
        saveAll(customer1, customer2, customer3, customer4);

        // when - filter is empty
        var results = repository.findAll(withPersonFilter(new PersonFilterDto(null, null, null)));
        // then
        assertEquals(4, results.size());

        // when - filter by firstName
        results = repository.findAll(withPersonFilter(new PersonFilterDto("Jan", null, null)));
        // then
        assertEquals(1, results.size());
        assertTrue(results.contains(customer1));

        // when - filter by lastName
        results = repository.findAll(withPersonFilter(new PersonFilterDto("Ada", "Kow", null)));
        // then
        assertEquals(1, results.size());
        assertTrue(results.contains(customer4));

        // when - filter by pesel
        results = repository.findAll(withPersonFilter(new PersonFilterDto(null, null, "928")));
        // then
        assertEquals(2, results.size());
        assertTrue(results.containsAll(asList(customer1, customer4)));
    }
}
