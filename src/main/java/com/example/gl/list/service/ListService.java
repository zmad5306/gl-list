package com.example.gl.list.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gl.list.dto.ListDto;
import com.example.gl.list.model.ListModel;
import com.example.gl.list.repository.ListRepository;

@Service
public class ListService {
	
	@Autowired
	private ListRepository repo;
	
	public List<ListDto> getAllLists(String username) {
		return repo.fetchAll(username).stream().map(lm -> new ListDto(lm)).collect(Collectors.toList());
	}
	
	public ListDto getList(String username, Long listId) {
		ListModel lm = repo.fetch(username, listId);
		return new ListDto(lm);
	}
	
	public ListDto newList(String username, ListDto list) {
		return new ListDto(repo.save(username, new ListModel(null, list.getName(), username)));
	}
	
	public void changeList(String username, ListDto list) {
		repo.update(username, new ListModel(list.getListId(), list.getName(), list.getUsername()));
	}
	
	public void deleteList(String username, Long listId) {
		repo.delete(username, listId);
	}

}
