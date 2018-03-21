package com.example.gl.list.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.gl.list.model.GenericApiResponse;
import com.example.gl.list.model.GroceryList;
import com.example.gl.list.model.Item;

@RestController
public class ListController {
	
	private List<GroceryList> lists;
	
	@PostConstruct
	public void init() {
		List<Item> itms1 = new ArrayList<>();
		
		itms1.add(new Item(1L, 1L, "item 1"));
		itms1.add(new Item(2L, 1L, "item 2"));
		itms1.add(new Item(3L, 1L, "item 3"));
		itms1.add(new Item(4L, 2L, "item 4"));
		itms1.add(new Item(5L, 3L, "item 5"));
		itms1.add(new Item(6L, 3L, "item 6"));
		
		List<Item> itms2 = new ArrayList<>();
		
		itms2.add(new Item(7L, 1L, "item 1"));
		itms2.add(new Item(8L, 1L, "item 2"));
		itms2.add(new Item(9L, 1L, "item 3"));
		itms2.add(new Item(10L, 2L, "item 4"));
		itms2.add(new Item(11L, 3L, "item 5"));
		itms2.add(new Item(12L, 3L, "item 6"));
		
		lists = new ArrayList<>();
		this.lists.add(new GroceryList(1L, "bob's list", "bob", itms1));
		this.lists.add(new GroceryList(2L, "sue's list", "sue", itms2));
	}
	
	List<GroceryList> getUsersLists(String username) {
		return this.lists.stream().filter(gl -> gl.getUsername().equals(username)).collect(Collectors.toList());
	}
	
	List<GroceryList> getListById(String username, Long listId) {
		return getUsersLists(username).stream().filter(gl -> gl.getId().equals(listId)).collect(Collectors.toList());
	}
	
	Long getNextListId() {
		OptionalLong max = this.lists.stream().map(gl -> gl.getId()).mapToLong(Long::longValue).max();
		return max.getAsLong() + 1L;
	}
	
	List<Item> getItem(GroceryList list, Long itemId) {
		return list.getItems().stream().filter(i -> i.getId().equals(itemId)).collect(Collectors.toList());
	}
	
	Long getNextItemId() {
		List<List<Item>> itemLists = new ArrayList<>();
		this.lists.stream().forEach(gl -> itemLists.add(gl.getItems()));
		List<Item> collect = itemLists.stream().collect(ArrayList::new, List::addAll, List::addAll);
		OptionalLong max = collect.stream().map(i -> i.getId()).mapToLong(Long::longValue).max();
		return max.getAsLong() + 1L;
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/"
		)
	public ResponseEntity<List<GroceryList>> getLists(@RequestHeader("sso_user") String username) {
		List<GroceryList> lists = getUsersLists(username);
		if (lists.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<>(lists, HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}"
		)
	public ResponseEntity<GroceryList> getList(@PathVariable Long listId, @RequestHeader("sso_user") String username) {
		List<GroceryList> lists = getListById(username, listId);
		if (lists.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		return new ResponseEntity<>(lists.get(0), HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/"
		)
	public ResponseEntity<GenericApiResponse> saveList(@RequestBody GroceryList list, @RequestHeader("sso_user") String username) {
		list.setItems(null);
		list.setUsername(username);
		list.setId(getNextListId());
		this.lists.add(list);
		return new ResponseEntity<>(new GenericApiResponse(), HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}"
		)
	public ResponseEntity<GenericApiResponse> updateList(@PathVariable Long listId, @RequestBody GroceryList list, @RequestHeader("sso_user") String username) {
		List<GroceryList> lists = getListById(username, listId);
		if (lists.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		GroceryList existingList = lists.get(0);
		
		list.setId(existingList.getId());
		list.setItems(existingList.getItems());
		list.setUsername(username);
		
		this.lists.remove(existingList);
		this.lists.add(list);
		
		return new ResponseEntity<>(new GenericApiResponse(), HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}"
		)
	public ResponseEntity<GenericApiResponse> deleteList(@PathVariable Long listId, @RequestHeader("sso_user") String username) {
		List<GroceryList> lists = getListById(username, listId);
		if (lists.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		this.lists.remove(lists.get(0));
		
		return new ResponseEntity<>(new GenericApiResponse(), HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}/item"
		)
	public ResponseEntity<List<Item>> getItems(@PathVariable Long listId, @RequestHeader("sso_user") String username) {
		List<GroceryList> lists = getListById(username, listId);
		if (lists.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		return new ResponseEntity<>(lists.get(0).getItems(), HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}/item/{departmentId}"
		)
	public ResponseEntity<List<Item>> getItems(@PathVariable Long listId, @PathVariable Long departmentId, @RequestHeader("sso_user") String username) {
		List<GroceryList> lists = getListById(username, listId);
		if (lists.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		List<Item> items = lists.get(0).getItems().stream().filter(i -> i.getDepartmentId().equals(departmentId)).collect(Collectors.toList());
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}/item"
		)
	public ResponseEntity<GenericApiResponse> saveItem(@PathVariable Long listId, @RequestBody Item item, @RequestHeader("sso_user") String username) {
		List<GroceryList> lists = getListById(username, listId);
		if (lists.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		item.setId(getNextItemId());
		
		lists.get(0).getItems().add(item);
		
		return new ResponseEntity<>(new GenericApiResponse(), HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}/item/{itemId}"
		)
	public ResponseEntity<GenericApiResponse> updateItem(@PathVariable Long listId, @PathVariable Long itemId, @RequestBody Item item, @RequestHeader("sso_user") String username) {
		List<GroceryList> lists = getListById(username, listId);
		if (lists.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		List<Item> items = getItem(lists.get(0), itemId);
		if (items.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		GroceryList list = lists.get(0);
		Item existingItem = items.get(0);
		
		item.setId(existingItem.getId());
		
		list.getItems().remove(existingItem);
		list.getItems().add(item);
				
		return new ResponseEntity<>(new GenericApiResponse(), HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}/item/{itemId}"
		)
	public ResponseEntity<GenericApiResponse> deleteItem(@PathVariable Long listId, @PathVariable Long itemId, @RequestHeader("sso_user") String username) {
		List<GroceryList> lists = getListById(username, listId);
		if (lists.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		List<Item> items = getItem(lists.get(0), itemId);
		if (items.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		lists.get(0).getItems().remove(items.get(0));
		
		return new ResponseEntity<>(new GenericApiResponse(), HttpStatus.OK);
	}

}
