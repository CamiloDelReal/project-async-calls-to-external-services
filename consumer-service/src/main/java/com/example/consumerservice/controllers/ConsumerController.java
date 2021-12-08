package com.example.consumerservice.controllers;

import com.example.consumerservice.entities.Address;
import com.example.consumerservice.entities.Contact;
import com.example.consumerservice.entities.Customer;
import com.example.consumerservice.entities.Name;
import com.example.consumerservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/consumer")
public class ConsumerController {

    private final CustomerService customerService;

    @Autowired
    public ConsumerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customers/{id}/broken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerByBroken(@PathVariable("id") Integer id) {
        Optional<Customer> customer  = customerService.getCustomerByBroken(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/customers/{id}/sync", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerBySync(@PathVariable("id") Integer id) {
        Optional<Customer> customer = customerService.getCustomerBySync(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/customers/{id}/async", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerByAsync(@PathVariable("id") Integer id) throws Exception {
        // It needs to be done here, doesn't work out a RestController
        CompletableFuture<Name> nameFuture = customerService.getCustomerNameAsync(id);
        CompletableFuture<Contact> contactFuture = customerService.getCustomerContactAsync(id);
        CompletableFuture<Address> addressFuture = customerService.getCustomerAddressAsync(id);
        CompletableFuture.allOf(nameFuture, contactFuture, addressFuture).join();
        if (nameFuture.get() == null && contactFuture.get() == null && addressFuture.get() == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new Customer(
                    id,
                    nameFuture.get(),
                    contactFuture.get(),
                    addressFuture.get()));
        }
    }

}
