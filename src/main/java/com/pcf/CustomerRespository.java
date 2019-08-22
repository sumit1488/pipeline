package com.pcf;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRespository extends JpaRepository<Customer, Long>{

	Customer findByLastName(String lastname);



	
}
