package com.example.externalservice.repositories;

import com.example.externalservice.entities.Address;
import com.example.externalservice.entities.Contact;
import com.example.externalservice.entities.Customer;
import com.example.externalservice.entities.Name;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private final List<Customer> customers;

    public CustomerRepository() {
        customers = new ArrayList<>() {{
            add(new Customer(
                    1,
                    new Name(
                            "John",
                            "Doe"
                    ),
                    new Contact(
                            "johndoe@mail.com",
                            "+1234567890"
                    ),
                    new Address(
                            "Country 1",
                            "City 1",
                            "Street and number where John lives",
                            "Second street and number",
                            11111
                    )
            ));
            add(new Customer(
                    2,
                    new Name(
                            "Jane",
                            "Doe"
                    ),
                    new Contact(
                            "janedoe@mail.com",
                            "+0987654321"
                    ),
                    new Address(
                            "Country 2",
                            "City 2",
                            "Street and number where Jane lives",
                            "Second street and number",
                            22222
                    )
            ));
        }};
    }

    public List<Customer> getAll() {
        return this.customers;
    }

    public Optional<Customer> getById(Integer id) {
        return customers.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst();
    }

    public Optional<Name> getNameById(Integer id) {
        return customers.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().map(Customer::getName);
    }

    public Optional<Contact> getContactById(Integer id) {
        return customers.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().map(Customer::getContact);
    }

    public Optional<Address> getAddressByid(Integer id) {
        return customers.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().map(Customer::getAddress);
    }

}
