package com.ordertrakingsystem.ordertracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ordertrakingsystem.ordertracking.entities.Customer;
import com.ordertrakingsystem.ordertracking.entities.Order;
import com.ordertrakingsystem.ordertracking.repository.CustomerRepo;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	CustomerRepo customerRepo;

	// 1
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@GetMapping("/customer/{id}")
	public List<Order> getCustomersById(@PathVariable("id") int id) {
		return customerRepo.getCustomersById(id);
	}

}
