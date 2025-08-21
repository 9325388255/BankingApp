package com.bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.bank.Entity.Customer;

import jakarta.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public Customer findByEmail(String email);

    public Customer findByPhone(String phone);
    
 // ✅ Deletes all customers who have no accounts
    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.accounts IS EMPTY")
    void deleteCustomersWithoutAccounts();
}
