package com.ordertrakingsystem.ordertracking.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderItemCK implements Serializable {

	@Column(name = "ref_order_id")
	private int orderId;

	@Column(name = "ref_product_id")
	private int productId;

	public OrderItemCK() {

	}

	public OrderItemCK(int orderId, int productId) {
		super();
		this.orderId = orderId;
		this.productId = productId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemCK other = (OrderItemCK) obj;
		return orderId == other.orderId && productId == other.productId;
	}

}
