package com.cognizant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.model.InventoryItem;

@Service
public interface InventoryItemService {

	public List<InventoryItem> getAllInventories();
	
	public InventoryItem getInventoryItemById(Long id);
	
	public InventoryItem addInventoryItem(InventoryItem item);
	
	public InventoryItem deleteInventoryItem(Long id);
	
	public InventoryItem updateInventoryItem(InventoryItem item);

	public InventoryItem getAvaliability(long id);
	
}
