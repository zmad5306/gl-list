package com.example.gl.list.model;

import org.springframework.data.annotation.Id;

import com.example.gl.list.dto.ListInputDto;

public class ListModel {
	
	@Id
	private String listId;
	private String name;
	private String username;
	
	public ListModel() {
		super();
	}

	public ListModel(String name, String username) {
		super();
		this.name = name;
		this.username = username;
	}
	
	public ListModel(ListInputDto list, String username) {
		super();
		this.name = list.getName();
		this.username = username;
	}
	
	public void apply(ListInputDto list) {
		this.name = list.getName();
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
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
