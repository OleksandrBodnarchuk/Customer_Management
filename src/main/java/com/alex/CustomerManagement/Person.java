package com.alex.CustomerManagement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Entity
@DiscriminatorValue("PERSON")
final class Person extends Customer {
    private String firstName;
    private String lastName;
    private String pesel;

    public Person(String firstName, String lastName, String pesel) {

        //  check for empty parameter
        this.firstName = requireNonNull(firstName);
        this.lastName = requireNonNull(lastName);
        ;
        this.pesel = requireNonNull(pesel);
        ;
    }

    // only for JPA
    private Person() {
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Person person = (Person) o;

        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;
        return pesel.equals(person.pesel);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + pesel.hashCode();
        return result;
    }
}
