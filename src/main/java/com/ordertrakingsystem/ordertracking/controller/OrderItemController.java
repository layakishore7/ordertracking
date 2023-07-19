package com.ordertrakingsystem.ordertracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ordertrakingsystem.ordertracking.repository.OrderItemRepo;

@RestController
@CrossOrigin
public class OrderItemController {

	@Autowired
	private OrderItemRepo orderItemsRepo;

	// 6
	@GetMapping("/orderitems/order")
	public List<Object[]> orderItemsOrder(@RequestParam("orderid") int orderid) {

		try {
			return orderItemsRepo.orderItemsByOrder(orderid);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"there is no order with the given orderid or invalid data");
		}
	}

	// 8
	@GetMapping("/orderitems/product/{productid}")
	public List<Object[]> orderItemsByProduct(@PathVariable("productid") int productid) {

		try {
			return orderItemsRepo.orderItemsByProduct(productid);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"there is no order with the given productid or invalid data");
		}
	}

}
