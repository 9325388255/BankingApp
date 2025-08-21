package com.bank.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String fullName;
	
	@Column(nullable = false, unique = true)
    private String email;
	
	@Column(nullable = false, unique = true)
    private String phone;
    
    @Column(nullable = false)
    private String address;
    
    @OneToMany(mappedBy = "customer", 
    		cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Account> accounts = new ArrayList();
}
