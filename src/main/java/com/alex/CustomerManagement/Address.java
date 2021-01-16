package com.alex.CustomerManagement;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "addresses")
final class Address {

    @Id
    private UUID id;
    private String Street;
    private String city;
    private String zipCode;
    private String country;

    @OnlyJpa
    private Address(){}

    public Address(String street, String city, String zipCode, String country) {
        this.id = UUID.randomUUID();
        Street = requireNonNull(street);
        this.city = requireNonNull(city);
        this.zipCode = requireNonNull(zipCode);
        this.country = requireNonNull(country);
    }

    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return Street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!id.equals(address.id)) return false;
        if (!Street.equals(address.Street)) return false;
        if (!city.equals(address.city)) return false;
        if (!zipCode.equals(address.zipCode)) return false;
        return country.equals(address.country);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Street.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + zipCode.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}
