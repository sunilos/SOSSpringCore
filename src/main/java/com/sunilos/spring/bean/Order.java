package com.sunilos.spring.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("order")

/**
 * Order bean. Payment and Inventory beans are injected in Order bean
 * 
 * @author Sunil Sahu
 * @Copyright (c) SunilOS Infotech Pvt Ltd
 *
 */
public class Order {

	/**
	 * Auto-wiring by Type
	 */
	@Autowired

	private Payment p = null;

	/**
	 * 
	 * Auto-wiring by Name using @Qualifier tag
	 * 
	 */
	@Autowired
	@Qualifier("inventory")
	private Inventory i = null;

	private int id = 0;
	private double amount = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void placeOrder(int qty, double price) {

		amount = qty * price;

		p.makePayment(amount);

		i.sold(qty);

		System.out.println("Balance: " + p.getBalance());

		System.out.println("Stock : " + i.getStock());

	}

	@PostConstruct
	public void init() {
		System.out.println("Order: init lifecycle method");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Order: destroy lifecycle method");
	}

}
