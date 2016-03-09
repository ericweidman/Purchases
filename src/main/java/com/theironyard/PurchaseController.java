package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ericweidman on 3/9/16.
 */
@Controller
public class PurchaseController {

    @Autowired
    PurchaseRepository purchases;

    @Autowired
    CustomerRepository customers;

    @PostConstruct
    public void parseCustomer() throws FileNotFoundException {
        File f = new File("customers.csv");
        Scanner scanner = new Scanner(f);
        scanner.nextLine();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String column[] = line.split(",");
            Customer customer = new Customer(column[0], column[1]);
            if(purchases.count() == 0) {
                customers.save(customer);
            }
        }
    }
    @PostConstruct
    public void parsePurchase() throws FileNotFoundException {
        File f = new File("purchases.csv");
        Scanner scanner = new Scanner(f);
        scanner.nextLine();
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String column[] = line.split(",");
            Purchase purchase = new Purchase(column[1], column[2], column[3], column[4]);
            if(purchases.count() == 0) {
                purchases.save(purchase);
            }
        }

    }

}


