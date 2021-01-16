package com.alex.CustomerManagement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.unmodifiableList;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type")
abstract class Customer {

    @Id
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private List<Address> addresses;

    // Constructor with auto id generator
    public Customer() {
        this.id = UUID.randomUUID();
        this.addresses = new ArrayList<>();
    }

    // Getter for Id
    public UUID getId() {
        return id;
    }


    public void addAddress(Address address) {
        if (!addresses.contains(address)) {
            addresses.add(address);
        }
    }

    public boolean removeAddress(Address address) {
        return addresses.remove(address);
    }

    public List<Address> getAddresses() {
        return unmodifiableList(addresses);
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
