package com.bank.Service;

import java.util.List;
import com.bank.Entity.Account;

public interface AccountService {
	
	public String createAccount(String accountNumber, Double initialBalance);
	public List<Account> getAllAccounts();
	public String deposit(String accountNumber, Double amount);
	public String withdraw(String accountNumber, Double amount);
	public Double checkBalance(String accountNumber);
	public String deleteAccount(String accountNumber);
    public String deleteAllAccounts();
}
