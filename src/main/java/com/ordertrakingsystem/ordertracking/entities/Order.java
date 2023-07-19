package com.ordertrakingsystem.ordertracking.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;

	@Column(name = "order_date")
	private LocalDate orderDate;

	@Column(name = "order_status")
	private char status;

	@Column(name = "order_deliverydate")
	private LocalDate deliveryDate;

	@Column(name = "ref_customer_id")
	private int ref_customer_id;
	
//	@MapsId("ref_customer_id")
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "ref_customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
	private Customer customer;

	@OneToMany(mappedBy = "order")
//	@JsonIgnore
	private List<OrderItem> orderItems;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public int getRef_customer_id() {
		return ref_customer_id;
	}

	public void setRef_customer_id(int ref_customer_id) {
		this.ref_customer_id = ref_customer_id;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
