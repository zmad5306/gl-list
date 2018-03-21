package com.example.gl.list.model;

public class ListModel {
	
	private Long listId;
	private String name;
	private String username;
	
	public ListModel() {
		super();
	}

	public ListModel(Long listId, String name, String username) {
		super();
		this.listId = listId;
		this.name = name;
		this.username = username;
	}

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
