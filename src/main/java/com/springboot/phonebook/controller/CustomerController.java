package com.springboot.phonebook.controller;
import com.springboot.phonebook.entity.Customer;
import com.springboot.phonebook.entity.Phone;
import com.springboot.phonebook.service.CustomerService;
import com.springboot.phonebook.service.PhoneService;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final PhoneService phoneService;

    public CustomerController(CustomerService customerService, PhoneService phoneService) {
        this.customerService = customerService;
        this.phoneService = phoneService;
    }

    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        List<Customer> theCustomers = customerService.getCustomers();
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @GetMapping("/showPhoneForm")
    public String showPhoneFormForAdd(Model theModel) {
        Phone phone = new Phone();
        List<Customer> customers = customerService.getCustomers();
        theModel.addAttribute("phone", phone);
        theModel.addAttribute("customers", customers);
        return "phone-form";
    }

    @PostMapping("/savePhone")
    public String savePhone(@ModelAttribute("phone") Phone phone,
                            @RequestParam int customerId) throws NotFoundException {
        Customer customer = customerService.getCustomer(customerId);
        phone.setCustomer(customer);
        phoneService.savePhone(phone);
        return "redirect:/customer/list";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
                                    Model theModel) throws NotFoundException {
        Customer theCustomer = customerService.getCustomer(theId);
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }
}
