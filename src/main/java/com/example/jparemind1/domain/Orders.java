package com.example.jparemind1.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToMany(mappedBy = "orders")
	private List<OrderBook> orderBooks = new ArrayList<>();

	@Enumerated(value = EnumType.STRING)
	private OrderStatus status;

	public void addOrderBook(final OrderBook orderBook) {
		orderBook.setOrders(this);
		this.orderBooks.add(orderBook);
	}

	@Builder
	public Orders(final Customer customer) {
		this.customer = customer;
	}


}
