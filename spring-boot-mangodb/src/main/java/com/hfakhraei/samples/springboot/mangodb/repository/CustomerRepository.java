package com.hfakhraei.samples.springboot.mangodb.repository;

import com.hfakhraei.samples.springboot.mangodb.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
}
