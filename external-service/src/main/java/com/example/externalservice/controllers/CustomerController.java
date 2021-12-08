package com.example.externalservice.controllers;

import com.example.externalservice.entities.Address;
import com.example.externalservice.entities.Contact;
import com.example.externalservice.entities.Customer;
import com.example.externalservice.entities.Name;
import com.example.externalservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    /**
     * The execution will take 20 seconds
     */
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) throws Exception {
        TimeUnit.SECONDS.sleep(20);
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * The execution will take 5 seconds
     */
    @GetMapping(path = "/{id}/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Name> getCustomerNameById(@PathVariable("id") Integer id) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        Optional<Name> name = customerService.getCustomerNameById(id);
        return name.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * The execution will take 4 seconds
     */
    @GetMapping(path = "/{id}/contact", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> getCustomerContactById(@PathVariable("id") Integer id) throws Exception {
        TimeUnit.SECONDS.sleep(4);
        Optional<Contact> contact = customerService.getCustomerContactById(id);
        return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * The execution will take 7 seconds
     */
    @GetMapping(path = "/{id}/address", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> getCustomerAddressById(@PathVariable("id") Integer id) throws Exception {
        TimeUnit.SECONDS.sleep(7);
        Optional<Address> address = customerService.getCustomerAddressById(id);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
