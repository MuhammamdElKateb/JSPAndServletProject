package com.item.Services;

import java.util.List; 

import com.item.model.Item;

public interface ItemService {
	Item getItemById(int id);
	List<Item> getItems();
	void addItem(Item item);
	void updateItem(Item item);
	void deleteItem(int id);

}
