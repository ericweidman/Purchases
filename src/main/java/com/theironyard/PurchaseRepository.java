package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ericweidman on 3/9/16.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
List<Purchase> findByCategory(String catgeory);
}
