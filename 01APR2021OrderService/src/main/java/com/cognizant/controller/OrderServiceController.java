package com.cognizant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.Order;
import com.cognizant.model.OrderItem;
import com.cognizant.service.InventoryService;
import com.cognizant.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderServiceController {
	
	private OrderService service;
	private InventoryService inventory;
	
	@GetMapping("/")
	public List<Order> getAll() {
		return service.getAllOrders();
	}
	
	@GetMapping("/{id}")
	public Order get(@PathVariable long id) {
		Order order = service.getOrderById(id);
		if(order == null)
			return new Order();
		return order;
	}
	
	@PostMapping("/")
	public Order add(@RequestBody Order order) {
		List<OrderItem> items = order.getItems();
		for(OrderItem item : items) {
			if(!inventory.isAvaliable(item.getProductId()))
				items.remove(item);
		}
		return service.addOrder(order);
	}
	
}
