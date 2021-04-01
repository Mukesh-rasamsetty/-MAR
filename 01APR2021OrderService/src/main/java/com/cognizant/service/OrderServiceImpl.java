package com.cognizant.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cognizant.model.Order;
import com.cognizant.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private OrderRepository repository;
	
	@Override
	@Transactional
	public List<Order> getAllOrders() {
		return repository.findAll();
	}

	@Override
	public Order getOrderById(long id) {
		Optional<Order> order= repository.findById(id);
		if(order.isPresent())
			return order.get();
		return null;
	}

	@Override
	public Order addOrder(Order order) {
		return repository.save(order);
	}

	@Override
	public Order deleteOrder(long id) {
		Optional<Order> order= repository.findById(id);
		if(order.isPresent()) {
			repository.deleteById(id);
			return order.get();
		}
		return null;
	}

	@Override
	public Order updateOrder(Order order) {
		Optional<Order> oldOrder= repository.findById(order.getId());
		if(oldOrder.isPresent()) {
			repository.save(order);
			return order;
		}
		return null;
	}

}
