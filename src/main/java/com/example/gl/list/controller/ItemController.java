package com.example.gl.list.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.gl.list.model.GroceryList;
import com.example.gl.list.model.Item;

@RestController
public class ItemController {
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}/item"
		)
	public ResponseEntity<List<Item>> getItems(@PathVariable Long listId) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}/item/{departmentId}"
		)
	public ResponseEntity<List<Item>> getItems(@PathVariable Long listId, @PathVariable Long departmentId) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}/item/{itemId}"
		)
	public ResponseEntity<GroceryList> getDepartment(@PathVariable Long listId) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}/item"
		)
	public ResponseEntity<?> saveDepartment(@PathVariable Long listId, @RequestBody Item item) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}/item/{itemId}"
		)
	public ResponseEntity<?> updateDepartment(@PathVariable Long listId, @PathVariable Long itemId, @RequestBody Item item) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}/item/{itemId}"
		)
	public ResponseEntity<?> deleteDepartment(@PathVariable Long listId, @PathVariable Long itemId) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}
