package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat250.jpa.tutorial.creditcards.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class CreditCardsMain {

  static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";

  public static void main(String[] args) {
    try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
         EntityManager em = factory.createEntityManager()) {

      em.getTransaction().begin();
      createObjects(em);
      em.getTransaction().commit();
    }
  }

  private static void createObjects(EntityManager em) {
    // Create a Pincode and persist it
    Pincode pincode = new Pincode();
    pincode.setCode("123");
    pincode.setCount(1);
    em.persist(pincode);

    // Create CreditCards and associate them with the Pincode
    CreditCard card1 = new CreditCard();
    card1.setPincode(pincode);
    card1.setCreditLimit(-10000);
    card1.setBalance(-5000);
    card1.setNumber(12345);
    em.persist(card1); // Persist the first card

    CreditCard card2 = new CreditCard();
    card2.setPincode(pincode);
    card2.setCreditLimit(2000);
    card2.setBalance(1);
    card2.setNumber(123);
    em.persist(card2); // Persist the second card

    // Create a Collection for CreditCards
    Collection<CreditCard> cards = new ArrayList<>();
    cards.add(card1);
    cards.add(card2);

    // Create a Bank and associate it with the CreditCards
    Bank bank = new Bank();
    bank.setName("Pengebank");
    bank.setCreditCards(cards);
    em.persist(bank); // Persist the bank

    // Create a Customer and associate it with the CreditCards
    Customer customer = new Customer();
    customer.setName("Max Mustermann");
    customer.setCreditCards(cards);
    em.persist(customer); // Persist the customer

    // Create an Address and associate it with the Customer
    Address address = new Address();
    address.setStreet("Inndalsveien");
    address.setNumber(28);
    em.persist(address); // Persist the address

    // Associate the Customer with the Address
    Collection<Address> addresses = new HashSet<>();
    addresses.add(address);
    customer.setAddresses(addresses); // Set the addresses for the customer

    // Associate the Address with the Customer
    Collection<Customer> customers = new HashSet<>();
    customers.add(customer);
    address.setCustomers(customers); // Set the customers for the address

    // Set the owning bank for the CreditCards
    card1.setOwningBank(bank);
    card2.setOwningBank(bank);
    // Persisting cards again here is not necessary since they are already persisted
  }
}
