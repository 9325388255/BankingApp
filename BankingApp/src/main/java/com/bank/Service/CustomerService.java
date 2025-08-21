package com.bank.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.Entity.Customer;

public interface CustomerService {
	
	public Customer saveCustomer(Customer customer);
	public List<Customer> getAllCustomers();
	public String deleteCustomerAccount(Long customerId, String accountNumber);
	void deleteCustomersWithoutAccounts();
}
