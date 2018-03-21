package com.example.gl.list.model;

public class Item {
	
	private Long id;
	private Long departmentId;
	private String name;
	
	public Item() {
		super();
	}
	public Item(Long id, Long departmentId, String name) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
