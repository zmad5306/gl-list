package com.example.gl.list.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.example.gl.list.model.ListModel;

@Repository
public class ListRepository {
	
	private List<ListModel> lists;
	
	List<ListModel> getLists(String username) {
		return this.lists.stream().filter(lm -> lm.getUsername().equalsIgnoreCase(username)).collect(Collectors.toList());
	}
	
	List<ListModel> getListsById(String username, Long listId) {
		return lists.stream().filter(lm -> lm.getListId() == listId).collect(Collectors.toList());
	}
	
	Long getNextListId() {
		OptionalLong max = this.lists.stream().map(gl -> gl.getListId()).mapToLong(Long::longValue).max();
		return max.getAsLong() + 1L;
	}
	
	@PostConstruct
	public void init() {
		lists = new ArrayList<>();
		this.lists.add(new ListModel(1L, "bob's list", "bob"));
		this.lists.add(new ListModel(2L, "sue's list", "sue"));
	}
	
	public List<ListModel> fetchAll(String username) {
		return getLists(username);
	}
	
	public ListModel fetch(String username, Long listId) {
		List<ListModel> lists = getListsById(username, listId);
		if (lists.isEmpty()) {
			return null;
		} else {
			return lists.get(0);
		}
	}
	
	public ListModel save(String username, ListModel list) {
		ListModel lm = new ListModel(getNextListId(), list.getName(), username);
		this.lists.add(lm);
		return lm;
	}
	
	public void update(String username, ListModel list) {
		List<ListModel> lists = getListsById(username, list.getListId());
		if (!lists.isEmpty()) {
			ListModel lm = lists.get(0);
			lm.setName(list.getName());
		}
	}
	
	public void delete(String username, Long listId) {
		List<ListModel> lists = getListsById(username, listId);
		if (!lists.isEmpty()) {
			ListModel lm = lists.get(0);
			this.lists.remove(lm);
		}
	}
	

}
