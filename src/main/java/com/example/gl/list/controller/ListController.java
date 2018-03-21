package com.example.gl.list.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.gl.list.dto.ListDto;
import com.example.gl.list.service.ListService;

@RestController
public class ListController {
	
	@Autowired
	private ListService service;

	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/"
		)
	public List<ListDto> getLists(@RequestHeader("sso_user") String username) {
		List<ListDto> lists = service.getAllLists(username);
		for (ListDto list: lists) {
			Link selfLink = linkTo(ListController.class).slash(list.getListId()).withSelfRel();
			list.add(selfLink);
			
			Link itemsLink = new Link("/api/items/" + list.getListId()).withTitle("items");
			list.add(itemsLink);
		}
		return lists;
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}"
		)
	public ResponseEntity<ListDto> getList(@PathVariable Long listId, @RequestHeader("sso_user") String username, @RequestHeader("x-forwarded-for") String fwdUrl) {
		ListDto list = service.getList(username, listId);
		if (list == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		Link selfLink = linkTo(ListController.class).slash(list.getListId()).withSelfRel();
		list.add(selfLink);
		
		Link itemsLink = new Link(fwdUrl + "/api/items/" + list.getListId()).withTitle("items");
		list.add(itemsLink);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/"
		)
	public ResponseEntity<ListDto> saveList(@RequestBody ListDto list, @RequestHeader("sso_user") String username, @RequestHeader("x-forwarded-for") String fwdUrl) {
		ListDto ld = service.newList(username, list);
		
		Link selfLink = linkTo(ListController.class).slash(ld.getListId()).withSelfRel();
		ld.add(selfLink);
		
		Link itemsLink = new Link(fwdUrl + "/api/items/" + list.getListId()).withTitle("items");
		list.add(itemsLink);
		
		return new ResponseEntity<>(ld, HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}"
		)
	public ResponseEntity<?> updateList(@PathVariable Long listId, @RequestBody ListDto list, @RequestHeader("sso_user") String username) {
		service.changeList(username, list);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			value="/{listId}"
		)
	public ResponseEntity<?> deleteList(@PathVariable Long listId, @RequestHeader("sso_user") String username) {
		service.deleteList(username, listId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
