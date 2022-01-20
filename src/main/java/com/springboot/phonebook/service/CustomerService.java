package com.springboot.phonebook.service;
import com.springboot.phonebook.entity.Customer;
import javassist.NotFoundException;
import java.util.List;

public interface CustomerService {

	List<Customer> getCustomers();

	void saveCustomer(Customer theCustomer);

	Customer getCustomer(int theId) throws NotFoundException;

	void deleteCustomer(int theId);
	
}
