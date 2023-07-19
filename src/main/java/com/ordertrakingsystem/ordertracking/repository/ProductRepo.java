package com.ordertrakingsystem.ordertracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ordertrakingsystem.ordertracking.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	// 7
	@Query("from Product p where  p.productName like %:name%")
	List<Product> getProductsByName(@Param("name") String name);

	// 9
	@Query("select p.price from Product p where p.productId = :id")
	Double priceOfProduct(@Param("id") Integer id);
	
	@Query("select p.description from Product p where p.productId = :id")
	List<Object[]> getProductDesc(@Param("id") int id);

}