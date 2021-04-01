package com.cognizant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cognizant.model.InventoryItem;
import com.cognizant.repository.InventoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryItemServiceImpl implements InventoryItemService{

	private InventoryRepository repository;
	
	@Override
	public List<InventoryItem> getAllInventories() {
		return repository.findAll();
	}

	@Override
	public InventoryItem getInventoryItemById(Long id) {
		Optional<InventoryItem> order= repository.findById(id);
		if(order.isPresent())
			return order.get();
		return null;
	}

	@Override
	public InventoryItem addInventoryItem(InventoryItem item) {
		return repository.save(item);
	}

	@Override
	public InventoryItem deleteInventoryItem(Long id) {
		Optional<InventoryItem> order= repository.findById(id);
		if(order.isPresent()) {
			repository.deleteById(id);
			return order.get();
		}
		return null;
	}

	@Override
	public InventoryItem updateInventoryItem(InventoryItem order) {
		Optional<InventoryItem> oldOrder= repository.findById(order.getId());
		if(oldOrder.isPresent()) {
			repository.save(order);
			return order;
		}
		return null;
	}

	@Override
	public InventoryItem getAvaliability(long id) {
		List<InventoryItem> list = repository.findAll();
		for(InventoryItem item : list) {
			if(item.getProductCode() == id)
				return item;
		}
		return null;
	}

	
}
