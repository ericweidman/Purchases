package com.theironyard;

import javax.persistence.*;

/**
 * Created by ericweidman on 3/9/16.
 */
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String date;
    @Column(nullable = false)
    String credit;
    @Column(nullable = false)
    String cvv;
    @Column(nullable = false)
    String category;


    @ManyToOne
    Customer customer;

    public Purchase() {
    }

    public Purchase(Customer customer, String date, String credit, String cvv, String category) {
        this.customer = customer;
        this.date = date;
        this.credit = credit;
        this.cvv = cvv;
        this.category = category;
    }
}
