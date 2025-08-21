package com.bank.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByAccountNumber(String accountNumber);
	
	void deleteByAccountNumber(String accountNumber);

	boolean existsByAccountNumber(String accountNumber);
}
