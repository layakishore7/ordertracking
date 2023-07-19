package com.ordertrakingsystem.ordertracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ordertrakingsystem.ordertracking.entities.Customer;
import com.ordertrakingsystem.ordertracking.repository.CustomerRepo;
import com.ordertrakingsystem.ordertracking.repository.OrderItemRepo;
import com.ordertrakingsystem.ordertracking.repository.OrderRepo;
import com.ordertrakingsystem.ordertracking.repository.ProductRepo;

@RestController
@CrossOrigin
public class NewController {

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	OrderItemRepo orderItemRepo;

	@Autowired
	ProductRepo productRepo;

	@GetMapping("/product/desc/{productid}")
	public List<Object[]> orderItemsByProduct(@PathVariable("productid") int productid) {

		try {
			return productRepo.getProductDesc(productid);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"there is no order with the given productid or invalid data");
		}
	}

	@GetMapping("/customers/nameslike/{name}")
	public List<Customer> getOrdersByname(@PathVariable("name") String name) {
		try {
			return customerRepo.getCustomersByName(name);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invaliddata !");
		}
	}

	@PostMapping("/newcustomer")
	public Customer addNewOrders(@RequestParam("customerid") Integer customerid,
			@RequestParam("customername") String customername, @RequestParam("email") String email,
			@RequestParam("mobile") String mobile) {
		try {
			Customer customer = new Customer();
			customer.setCustomerId(customerid);
			customer.setCustomerName(customername);
			customer.setEmail(email);
			customer.setMobile(mobile);
			customerRepo.save(customer);
			return customer;
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid data");
		}
	}

	@PostMapping("/newcustomers")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
		customer.getCustomerId();
		customer.getCustomerName();
		customer.getEmail();
		customer.getMobile();
		customerRepo.save(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body("inserted");
	}

	@PutMapping("/updatecustomername")
	public Customer UpdateCustomers(@RequestParam("customerid") Integer customerid, @RequestParam("name") String name) {

		if (name.length() == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid name");
		}
		var customer = customerRepo.findById(customerid);
		if (customer.isPresent()) {
			var c = customer.get();
			c.setCustomerName(name);
			customerRepo.save(c);
			return c;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "customerid not found");
		}
	}

	@DeleteMapping("/deletecustomer")
	public void DeleteCustomerById(@RequestParam("customerid") Integer customerid) {
		var customer = customerRepo.findById(customerid);
		if (customer.isPresent()) {
			customerRepo.deleteById(customerid);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "customerid not found");
		}
	}

}
