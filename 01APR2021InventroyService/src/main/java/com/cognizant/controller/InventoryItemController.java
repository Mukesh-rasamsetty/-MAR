package com.cognizant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.InventoryItem;
import com.cognizant.service.InventoryItemService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/inventory")
public class InventoryItemController {

	private InventoryItemService service;
	
	@GetMapping("/")
	public List<InventoryItem> getAll() {
		return service.getAllInventories();
	}
	
	@GetMapping("/{id}")
	public InventoryItem getAll(@PathVariable long id) {
		InventoryItem item = service.getInventoryItemById(id);
		if(item == null)
			return new InventoryItem();
		return item;
	}
	
	@GetMapping("/checking/{id}")
	public int is(@PathVariable long id) {
		InventoryItem item = service.getAvaliability(id);
		if(item != null) {
			int quantity = item.getAvailableQuantity();
			if(quantity > 0) {
			item.setAvailableQuantity(quantity-1);
			service.updateInventoryItem(item);
			}
			return quantity;
		}
		return -2;
	}
	
	@PostMapping("/")
	public InventoryItem add(@RequestBody InventoryItem item) {
		return service.addInventoryItem(item);
	}
	
	@PutMapping("/")
	public InventoryItem update(@RequestBody InventoryItem item) {
		InventoryItem temp = service.updateInventoryItem(item);
		if(temp==null)
			return new InventoryItem();
		return temp;
	}
	
	@DeleteMapping("/{id}")
	public InventoryItem delete(@PathVariable long id) {
		InventoryItem temp = service.deleteInventoryItem(id);
		if(temp==null)
			return new InventoryItem();
		return temp;
	}
}
