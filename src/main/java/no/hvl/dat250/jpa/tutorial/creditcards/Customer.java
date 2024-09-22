package no.hvl.dat250.jpa.tutorial.creditcards;

import java.util.Collection;
import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private Collection<Address> addresses;

    @ManyToMany
    private Collection<CreditCard> creditCards;

    public Customer(Collection<CreditCard> creditCards, Collection<Address> addresses, String name) {
        this.creditCards = creditCards;
        this.addresses = addresses;
        this.name = name;
    }

    public Customer() {

    }

    public String getName() {
        return name;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public Collection<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Collection<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    public void setName(String name) {
        this.name = name;
    }
}
