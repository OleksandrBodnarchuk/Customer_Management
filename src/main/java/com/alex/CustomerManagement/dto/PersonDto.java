package com.alex.CustomerManagement.dto;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class PersonDto {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String pesel;

    public PersonDto(UUID id, String firstName, String lastName, String pesel) {
        this.id = requireNonNull(id);
        this.firstName = requireNonNull(firstName);
        this.lastName = requireNonNull(lastName);
        this.pesel = requireNonNull(pesel);
    }

    public UUID getId() {
        return id;
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

        PersonDto personDto = (PersonDto) o;

        if (!id.equals(personDto.id)) return false;
        if (!firstName.equals(personDto.firstName)) return false;
        if (!lastName.equals(personDto.lastName)) return false;
        return pesel.equals(personDto.pesel);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + pesel.hashCode();
        return result;
    }
}
