package com.example.gl.list.dto;

import org.springframework.hateoas.ResourceSupport;

import com.example.gl.list.model.ListModel;

public class ListDto extends ResourceSupport {
	
	private Long listId;
	private String name;
	private String username;
	
	public ListDto(ListModel list) {
		if (null != list) {
			this.listId = list.getListId();
			this.name = list.getName();
			this.username = list.getUsername();
		}
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
