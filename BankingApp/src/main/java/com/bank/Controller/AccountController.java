package com.bank.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bank.Entity.Account;
import com.bank.Service.AccountService;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@PostMapping("/create")
    public String createAccount(@RequestParam String accountNumber,
                                @RequestParam Double initialBalance) {
        return service.createAccount(accountNumber, initialBalance);
    }
	
	@GetMapping("/all")
	public List<Account> getAllAccounts() {
	    return service.getAllAccounts();
	}
	
	@PutMapping("/deposit")
    public String deposit(@RequestParam String accountNumber,
                          @RequestParam Double amount) {
        return service.deposit(accountNumber, amount);
    }

    @PutMapping("/withdraw")
    public String withdraw(@RequestParam String accountNumber,
                           @RequestParam Double amount) {
        return service.withdraw(accountNumber, amount);
    }
	 
	 @GetMapping("/balance")
	 public Double checkBalance(@RequestParam String accountNumber) {
	      return service.checkBalance(accountNumber);
	 }
	 
	 @DeleteMapping("/delete")
	 public String deleteAccount(@RequestParam String accountNumber) {
	     return service.deleteAccount(accountNumber);
	 }

	 @DeleteMapping("/deleteAll")
	 public String deleteAllAccounts() {
	      return service.deleteAllAccounts();
	 }
}
