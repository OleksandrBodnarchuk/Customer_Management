package com.alex.CustomerManagement.dto;

public final class CreatePersonDto {

    private final String firstName;
    private final String lastName;
    private final String pesel;

    public CreatePersonDto(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
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

        CreatePersonDto that = (CreatePersonDto) o;

        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        return pesel.equals(that.pesel);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + pesel.hashCode();
        return result;
    }
}
