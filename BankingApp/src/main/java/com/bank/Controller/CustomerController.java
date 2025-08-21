package com.bank.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bank.Entity.Customer;
import com.bank.Repository.CustomerRepository;
import com.bank.Service.AccountService;
import com.bank.Service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
	private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer saved = customerService.saveCustomer(customer);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    
    @DeleteMapping("/{customerId}/accounts/{accountNumber}")
    public ResponseEntity<String> deleteAccountFromCustomer(
            @PathVariable Long customerId,
            @PathVariable String accountNumber) {

        String response = customerService.
        		deleteCustomerAccount(customerId, accountNumber);

        if (response.contains("successfully")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    @DeleteMapping("/delete-without-accounts")
    public ResponseEntity<String> deleteCustomersWithoutAccounts() {
        customerService.deleteCustomersWithoutAccounts();
        return ResponseEntity.ok
        		("All customers without accounts deleted successfully!");
    }
}