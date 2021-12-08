package com.example.consumerservice.services;

import com.example.consumerservice.entities.Address;
import com.example.consumerservice.entities.Contact;
import com.example.consumerservice.entities.Customer;
import com.example.consumerservice.entities.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomerService {

    @Value("${external.customers.base-url}")
    private String baseUrl;
    @Value("${external.customers.paths.name}")
    private String namePath;
    @Value("${external.customers.paths.contact}")
    private String contactPath;
    @Value("${external.customers.paths.address}")
    private String addressPath;

    private final RestTemplate restTemplate;

    @Autowired
    public CustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Customer> getCustomerByBroken(Integer id) {
        return Optional.ofNullable(restTemplate.getForObject(baseUrl + "/" + id, Customer.class));
    }

    public Optional<Customer> getCustomerBySync(Integer id) {
        Name name = restTemplate.getForObject(baseUrl + "/" + id + "/" + namePath, Name.class);
        Contact contact = restTemplate.getForObject(baseUrl + "/" + id + "/" + contactPath, Contact.class);
        Address address = restTemplate.getForObject(baseUrl + "/" + id + "/" + addressPath, Address.class);
        if (name == null && contact == null && address == null) {
            return Optional.empty();
        } else {
            return Optional.of(new Customer(
                    id,
                    name,
                    contact,
                    address
            ));
        }
    }

    @Async("customerAsyncExecutor")
    public CompletableFuture<Name> getCustomerNameAsync(Integer id) {
        Name name = restTemplate.getForObject(baseUrl + "/" + id + "/" + namePath, Name.class);
        return CompletableFuture.completedFuture(name);
    }

    @Async("customerAsyncExecutor")
    public CompletableFuture<Contact> getCustomerContactAsync(Integer id) {
        Contact contact = restTemplate.getForObject(baseUrl + "/" + id + "/" + contactPath, Contact.class);
        return CompletableFuture.completedFuture(contact);
    }

    @Async("customerAsyncExecutor")
    public CompletableFuture<Address> getCustomerAddressAsync(Integer id) {
        Address address = restTemplate.getForObject(baseUrl + "/" + id + "/" + addressPath, Address.class);
        return CompletableFuture.completedFuture(address);
    }

}
