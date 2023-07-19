package com.ordertrakingsystem.ordertracking.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ordertrakingsystem.ordertracking.entities.Order;
import com.ordertrakingsystem.ordertracking.entities.OrderItem;
import com.ordertrakingsystem.ordertracking.entities.OrderItemCK;
import com.ordertrakingsystem.ordertracking.repository.CustomerRepo;
import com.ordertrakingsystem.ordertracking.repository.OrderItemRepo;
import com.ordertrakingsystem.ordertracking.repository.OrderRepo;
import com.ordertrakingsystem.ordertracking.repository.ProductRepo;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	OrderItemRepo orderitemsrepo;

	@Autowired
	CustomerRepo customerrepo;

	@Autowired
	ProductRepo productrepo;

	// 3
	@GetMapping("/orders/{customerid}")
	public List<Order> getOrdersById(@PathVariable("customerid") String customerid) {
		try {
			return orderRepo.getOrdersByCustomerid(customerid);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"there is no orders on given customerid or invalid data !");
		}
	}

	// 4
	@GetMapping("/orders/date/{orderdate}")
	public List<Order> getOrdersBydate(@PathVariable("orderdate") LocalDate orderdate) {
		try {
			return orderRepo.getOrdersByDate(orderdate);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"there is no orders on given date or invalid data !");
		}

	}

	// 5
	@GetMapping("/orders/status/{status}")
	public List<Order> getOrdersBystatus(@PathVariable("status") Character status) {
		try {
			return orderRepo.getOrdersByStatus(status);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"there is no orders on given status or invalid data !");
		}

	}

	// 9
	@PostMapping("/neworder")
	public Order addNewOrders(@RequestParam("customerid") Integer customerid,
			@RequestParam("productid") List<Integer> productid, @RequestParam("quantity") List<Integer> quantity) {
		try {
//			Customer customer = customerrepo.findById(customerid).get();
			Order order = new Order();
			order.setRef_customer_id(customerid);
			LocalDate date = LocalDate.now();
			order.setOrderDate(date);
			order.setStatus('n');
			orderRepo.save(order);
			int id = orderRepo.max();
			List<OrderItem> loi = new ArrayList<>();
			for (int i = 0; i < productid.size(); i++) {
				OrderItem oi = new OrderItem();
				OrderItemCK ck = new OrderItemCK();
				ck.setOrderId(id);
				ck.setProductId(productid.get(i));
				oi.setOrdck(ck);
				oi.setQty(quantity.get(i));
				Double price = productrepo.priceOfProduct(productid.get(i));
				oi.setPrice(quantity.get(i) * price);
				loi.add(oi);
			}
			order.setOrderItems(loi);
			orderitemsrepo.saveAll(loi);
			return order;
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invaliddata !");
		}
	}

	// 10
	@GetMapping("/orders/details")
	public List<Order> getDetailsByOrder(@RequestParam("orderid") int orderid) {
		return orderRepo.getDetailsByOrder(orderid);
	}

}
