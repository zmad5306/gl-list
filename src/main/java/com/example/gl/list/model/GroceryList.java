package com.example.gl.list.model;

import java.util.List;

public class GroceryList {
	
	private Long id;
	private String name;
	private String username;
	private List<Item> items;
	
	public GroceryList() {
		super();
	}
	public GroceryList(Long id, String name, String username, List<Item> items) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.items = items;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

}
