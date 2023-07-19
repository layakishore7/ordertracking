package com.ordertrakingsystem.ordertracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ordertrakingsystem.ordertracking.entities.Product;
import com.ordertrakingsystem.ordertracking.repository.ProductRepo;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	ProductRepo productRepo;

	// 2
	@GetMapping("/products/page")
	public List<Product> getEntities(@RequestParam("number") Integer number, @RequestParam("size") Integer size) {
		var products = productRepo.findAll(PageRequest.of(number, size));
		return products.getContent();
	}

	// 7
	@GetMapping("/products/nameslike/{name}")
	public List<Product> getOrdersByname(@PathVariable("name") String name) {
		try {
			return productRepo.getProductsByName(name);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invaliddata !");
		}
	}

}
