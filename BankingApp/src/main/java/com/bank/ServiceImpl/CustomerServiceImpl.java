package com.bank.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.Entity.Account;
import com.bank.Entity.Customer;
import com.bank.Repository.AccountRepository;
import com.bank.Repository.CustomerRepository;
import com.bank.Service.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService  {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		if (customer.getAccounts() != null) {
	        for (Account account : customer.getAccounts()) {

	            // Check in database before saving
	            if (accountRepository.existsByAccountNumber(account.getAccountNumber())) {
	                throw new RuntimeException(
	                    "Account number already exists: " + account.getAccountNumber()
	                );
	            }

	            // Set customer reference for each account
	            account.setCustomer(customer);
	        }
		}
	return customerRepository.save(customer);
	} 
	
	
	
	@Override
    public String deleteCustomerAccount(Long customerId, String accountNumber) {
        Optional<Customer> optionalCustomer = customerRepository.
        		findById(customerId);
        if (optionalCustomer.isEmpty()) {
            return "Customer not found!";
        }

        Customer customer = optionalCustomer.get();

        Optional<Account> optionalAccount = accountRepository.
        		findByAccountNumber(accountNumber);
        if (optionalAccount.isEmpty()) {
            return "Account not found!";
        }

        Account account = optionalAccount.get();

        // Check if this account belongs to the given customer
        if (!account.getCustomer().getId().equals(customer.getId())) {
            return "This account does not belong to the provided customer!";
        }

        // Remove account from customer's account list
        customer.getAccounts().remove(account);

        // Delete the account
        accountRepository.delete(account);

        return "Account " + accountNumber + " deleted successfully for customer: " + customer.getFullName();
    }

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll(); 
	}

	@Transactional
    @Override
    public void deleteCustomersWithoutAccounts() {
        customerRepository.deleteCustomersWithoutAccounts();
    }
}
