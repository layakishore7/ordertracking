package com.ordertrakingsystem.ordertracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ordertrakingsystem.ordertracking.entities.Customer;
import com.ordertrakingsystem.ordertracking.entities.Order;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	// 5
		@Query("from Customer  where customerId = :id")
		List<Order> getCustomersById(@Param("id") int id);
		
		
		@Query("from Customer c where  c.customerName like %:name%")
		List<Customer> getCustomersByName(@Param("name") String name);

		

}
