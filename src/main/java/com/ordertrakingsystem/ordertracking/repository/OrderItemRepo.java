package com.ordertrakingsystem.ordertracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ordertrakingsystem.ordertracking.entities.OrderItem;
import com.ordertrakingsystem.ordertracking.entities.OrderItemCK;

public interface OrderItemRepo extends JpaRepository<OrderItem, OrderItemCK> {

	// 6
	@Query("select o.product.productName , o.qty, o.price from OrderItem o where o.order.orderId = :id")
	List<Object[]> orderItemsByOrder(@Param("id") int id);

	// 8
	@Query("select o.product.productName, o.order.customer.customerName, o.qty, o.price, o.order.orderDate from OrderItem o  where o.product.productId = :id")
	List<Object[]> orderItemsByProduct(@Param("id") int id);

}
