package com.springboot.phonebook.controller;
import com.springboot.phonebook.entity.Customer;
import com.springboot.phonebook.service.CustomerService;
import com.springboot.phonebook.service.PhoneService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/rest")
public class RestCustomerController {

    private final CustomerService customerService;
    private final PhoneService phoneService;

    public RestCustomerController(CustomerService customerService, PhoneService phoneService) {
        this.customerService = customerService;
        this.phoneService = phoneService;
    }

    @GetMapping("/customers")
    List<Customer> all() {
        return customerService.getCustomers();
    }

    @PostMapping("/saveCustomer")
    void newCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
    }

    @GetMapping("/customers/{id}")
    Customer one(@PathVariable int id) throws NotFoundException {
        return customerService.getCustomer(id);
    }

    @PutMapping("/customers/{id}")
    void replaceCustomer(@RequestBody Customer customer, @PathVariable int id) throws NotFoundException {
        Customer customer1 = customerService.getCustomer(id);
        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setEmail(customer.getEmail());
        customer1.setPhones(customer.getPhones());
        customerService.saveCustomer(customer1);
    }

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
}
