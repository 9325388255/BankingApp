package com.bank.ServiceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bank.Entity.Account;
import com.bank.Entity.Customer;
import com.bank.Exception.AccountNotFoundException;
import com.bank.Repository.AccountRepository;
import com.bank.Repository.CustomerRepository;
import com.bank.Service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository repo;
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public String createAccount(String accountNumber, Double initialBalance) {
		
		if (repo.findByAccountNumber(accountNumber).isPresent()) {
	        return "❌ Account number already exists. Please choose another.";
	    }
		
		Account acc = new Account();
		acc.setAccountNumber(accountNumber);
        acc.setBalance(initialBalance);
        repo.save(acc);
        return "Account created successfully!";
	}

	@Override
	public String deposit(String accountNumber, Double amount) {
		
		 Account acc = repo.findByAccountNumber(accountNumber)
                 .orElseThrow(() -> new AccountNotFoundException
                		 ("❌ Account not found: " + accountNumber));

         acc.setBalance(acc.getBalance() + amount);
         repo.save(acc);
         return "✅ Deposit successful. New Balance: " + acc.getBalance();
	}

	@Override
	 public String withdraw(String accountNumber, Double amount) {
        
		Account acc = repo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException
                		("Account not found: " + accountNumber));
        
        if (acc.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        acc.setBalance(acc.getBalance() - amount);
        repo.save(acc);
        return "Withdraw successful. New Balance: " + acc.getBalance();
    }

	@Override
	public Double checkBalance(String accountNumber) {
		Account acc = repo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException
                		("Account not found: " + accountNumber));

        return acc.getBalance();
	}

	@Override
	public List<Account> getAllAccounts() {
	    return repo.findAll();
	}
	
	@Override
	public String deleteAccount(String accountNumber) {
		// Find account by account number
        Account account = repo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found with number: " + accountNumber));

        // Get associated customer
        Customer customer = account.getCustomer();

        // Remove account from customer's account list
        customer.getAccounts().remove(account);

        // Save the customer to update the relationship
        customerRepo.save(customer);

        // Delete the account safely
        repo.delete(account);
		return "Account Deleted Successfully " + accountNumber;
	}

	    // ✅ Delete all accounts
	@Override
	 public String deleteAllAccounts() {
	        repo.deleteAll();
	        return "✅ All accounts deleted successfully";
	 }
}
