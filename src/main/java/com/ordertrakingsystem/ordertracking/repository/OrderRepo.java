package com.ordertrakingsystem.ordertracking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ordertrakingsystem.ordertracking.entities.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

	// 3
	@Query("from Order o where o.customer.customerId = :customerid")
	List<Order> getOrdersByCustomerid(@Param("customerid") String customerid);

	// 4
	@Query("from Order  where orderDate = :orderdate")
	List<Order> getOrdersByDate(@Param("orderdate") LocalDate orderdate);

	// 5
	@Query("from Order  where status = :status")
	List<Order> getOrdersByStatus(@Param("status") Character status);

	// 9
	@Query("select max(o.orderId) from Order o")
	int max();

	// 10
	@Query("select o from Order o JOIN FETCH o.orderItems where o.orderId = :id")
	List<Order> getDetailsByOrder(@Param("id") int id);

}
