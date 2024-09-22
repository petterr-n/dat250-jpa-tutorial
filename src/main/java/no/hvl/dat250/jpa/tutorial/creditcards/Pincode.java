package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;

@Entity
public class Pincode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private Integer count;

    public Pincode(Integer count, String code) {
        this.count = count;
        this.code = code;
    }

    public Pincode() {

    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
