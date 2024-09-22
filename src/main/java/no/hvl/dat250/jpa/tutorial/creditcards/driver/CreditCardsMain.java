package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat250.jpa.tutorial.creditcards.*;
import org.hibernate.collection.spi.PersistentBag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CreditCardsMain {

  static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";

  public static void main(String[] args) {
    try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(
        PERSISTENCE_UNIT_NAME); EntityManager em = factory.createEntityManager()) {
      em.getTransaction().begin();
      createObjects(em);
      em.getTransaction().commit();
    }

  }

  private static void createObjects(EntityManager em) {
    // TODO: Create object world as shown in the README.md.

    Pincode pincode = new Pincode();
    pincode.setCode("123");
    pincode.setCount(1);
    em.persist(pincode);

    CreditCard card1 = new CreditCard();
    card1.setPincode(pincode);
    card1.setCreditLimit(-10000);
    card1.setBalance(-5000);
    card1.setNumber(12345);

    CreditCard card2 = new CreditCard();
    card2.setPincode(pincode);
    card2.setCreditLimit(2000);
    card2.setBalance(1);
    card2.setNumber(123);

    em.persist(card1);
    em.persist(card2);

    Collection<CreditCard> cards = new ArrayList<>();
    cards.add(card1);
    cards.add(card2);

    Bank bank = new Bank();
    bank.setName("Pengebank");
    bank.setCreditCards(cards);
    em.persist(bank);

    Customer customer = new Customer();
    customer.setName("Max Mustermann");
    customer.setCreditCards(cards);
    em.persist(customer);

    Address address = new Address();
    address.setStreet("Inndalsveien");
    address.setNumber(28);
    em.persist(address);

    Collection<Customer> customers = new HashSet<>();
    Collection<Address> addresses = new HashSet<>();

    customers.add(customer);
    addresses.add(address);

    customer.setAddresses(addresses);
    customer.setCreditCards(cards);
    em.persist(customer);

    address.setCustomers(customers);
    em.persist(address);

    card1.setOwningBank(bank);
    card2.setOwningBank(bank);
    em.persist(card1);
    em.persist(card2);


  }
}
