package com.cognizant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.model.Order;

@Service
public interface OrderService {

	public List<Order> getAllOrders();

	public Order getOrderById(long id);

	public Order addOrder(Order order);

	public Order deleteOrder(long id);

	public Order updateOrder(Order order);

}
