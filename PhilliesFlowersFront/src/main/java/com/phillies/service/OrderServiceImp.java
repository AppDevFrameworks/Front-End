package com.phillies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phillies.domain.Order;
import com.phillies.repository.OrderRepo;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private OrderRepo orderRepo;
	
	@Override
	public Order getLastOrder() {
		List<Order> orders = orderRepo.findAll(); 
		Order order = null;
		for(int i = 0; i < orders.size(); i++) {
			order = orders.get(i);
		}
		return order;
	}

	@Override
	public void createOrder(Order order) {
		orderRepo.save(order);
	}

	@Override
	public int getNextId() {
		int nextId = (int) orderRepo.count();
		return nextId;
	}
}