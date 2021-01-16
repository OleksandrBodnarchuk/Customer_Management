package com.alex.CustomerManagement;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type")
abstract class Customer {

    @Id
    private UUID id;

    // Constructor with auto id generator
    public Customer() {
        this.id = UUID.randomUUID();
    }

    // Getter for Id
    public UUID getId() {
        return id;
    }

    // adding non null equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
