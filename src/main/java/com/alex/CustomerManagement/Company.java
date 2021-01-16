package com.alex.CustomerManagement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static java.util.Objects.requireNonNull;

@Entity
@DiscriminatorValue("COMPANY")
final class Company extends Customer{

    private String name;
    private String vatNumber;

    @OnlyJpa
    private Company() {
    }

    public Company(String name, String vatNumber) {
        this.name = requireNonNull(name);
        this.vatNumber = requireNonNull(vatNumber);
    }

    public String getName() {
        return name;
    }

    public String getVatNumber() {
        return vatNumber;
    }

}
