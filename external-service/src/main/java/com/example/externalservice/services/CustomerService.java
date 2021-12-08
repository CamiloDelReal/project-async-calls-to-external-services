package com.example.externalservice.services;

import com.example.externalservice.entities.Address;
import com.example.externalservice.entities.Contact;
import com.example.externalservice.entities.Customer;
import com.example.externalservice.entities.Name;
import com.example.externalservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.getById(id);
    }

    public Optional<Name> getCustomerNameById(Integer id) {
        return customerRepository.getNameById(id);
    }

    public Optional<Contact> getCustomerContactById(Integer id) {
        return customerRepository.getContactById(id);
    }

    public Optional<Address> getCustomerAddressById(Integer id) {
        return customerRepository.getAddressByid(id);
    }

}
