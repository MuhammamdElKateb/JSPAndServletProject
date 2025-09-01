package com.item.model;

public class Item {
	private int id;
	private String name;
	private double price;
	private int totalNumber;
	
	
	public Item() {
		super();
	}
	public Item(String name, double price, int totalNumber) {
		super();
		this.name = name;
		this.price = price;
		this.totalNumber = totalNumber;
		
		//id generated automatically from the data base
	}
	public Item(int id, String name, double price, int totalNumber) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.totalNumber = totalNumber;
		
		//id generated manually from the data base
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

}
