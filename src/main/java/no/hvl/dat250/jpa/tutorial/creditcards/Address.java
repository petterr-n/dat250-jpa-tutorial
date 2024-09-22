package no.hvl.dat250.jpa.tutorial.creditcards;

import java.util.Collection;
import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private Integer number;

    @ManyToMany
    private Collection<Customer> customers;

    public Address(Collection<Customer> customers, Integer number, String street) {
        this.customers = customers;
        this.number = number;
        this.street = street;
    }

    public Address() {

    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public Collection<Customer> getOwners() {
        return customers;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }
}
