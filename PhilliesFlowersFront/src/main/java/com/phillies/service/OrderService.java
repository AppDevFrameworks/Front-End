package com.phillies.service;

import com.phillies.domain.Order;

public interface OrderService {
	public Order getLastOrder();
	public void createOrder(Order order);
	public int getNextId();
}