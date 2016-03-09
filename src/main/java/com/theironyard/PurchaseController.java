package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        if (customers.count() == 0) {
            File f = new File("customers.csv");
            Scanner scanner = new Scanner(f);
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String column[] = line.split(",");
                Customer customer = new Customer(column[0], column[1]);
                customers.save(customer);
            }
        }
        if (purchases.count() == 0) {
            File f = new File("purchases.csv");
            Scanner scanner = new Scanner(f);
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String column[] = line.split(",");
                Customer customer = customers.findOne(Integer.valueOf(column[0]));
                Purchase purchase = new Purchase(customer, column[1], column[2], column[3], column[4]);
                purchases.save(purchase);
            }
        }
    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String category){
        if (category != null) {
            model.addAttribute("purchases", purchases.findByCategory(category));
        }
        else{
            model.addAttribute("purchases", purchases.findAll());
        }
            return "home";
    }
}
