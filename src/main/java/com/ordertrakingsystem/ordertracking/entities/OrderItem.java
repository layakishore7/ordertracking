package com.ordertrakingsystem.ordertracking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

	@EmbeddedId
	private OrderItemCK ordck;

	@Column(name = "qty")
	private Integer qty;

	@Column(name = "total_price")
	private Double price;

	@MapsId("ref_order_id")
	@ManyToOne//(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "ref_order_id", referencedColumnName = "order_id")
	@JsonIgnore
	private Order order;

	@MapsId("ref_product_id")
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "ref_product_id", referencedColumnName = "product_id")
	private Product product;

	public OrderItemCK getOrdck() {
		return ordck;
	}

	public void setOrdck(OrderItemCK ordck) {
		this.ordck = ordck;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Order getOrders() {
		return order;
	}

	public void setOrders(Order order) {
		this.order = order;
	}

	public Product getProducts() {
		return product;
	}

	public void setProducts(Product product) {
		this.product = product;
	}

}
