package com.example.gl.list;

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

@RestController
public class ListController {
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/"
		)
	public ResponseEntity<List<GroceryList>> getLists() {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}"
		)
	public ResponseEntity<GroceryList> getList(@PathVariable Long listId) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/"
		)
	public ResponseEntity<?> saveList(@RequestBody GroceryList department) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}"
		)
	public ResponseEntity<?> updateList(@PathVariable Long listId, @RequestBody GroceryList list) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}"
		)
	public ResponseEntity<?> deleteList(@PathVariable Long listId) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
