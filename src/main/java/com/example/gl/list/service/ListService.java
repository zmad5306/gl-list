package com.example.gl.list.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gl.list.dto.ListDto;
import com.example.gl.list.dto.ListInputDto;
import com.example.gl.list.model.ListModel;
import com.example.gl.list.repository.ListRepository;

@Service
public class ListService {
	
	@Autowired
	private ListRepository repo;
	
	public List<ListDto> getAllLists(String username) {
		return repo.findByUsername(username).stream().map(lm -> new ListDto(lm)).collect(Collectors.toList());
	}
	
	public ListDto getList(String username, String listId) {
		ListModel lm = repo.findByListIdAndUsername(listId, username);
		return new ListDto(lm);
	}
	
	public ListDto insertList(String username, ListInputDto list) {
		ListModel lm = new ListModel(list, username);
		return new ListDto(repo.insert(lm));
	}
	
	public void saveList(String listId, String username, ListInputDto list) {
		ListModel lm = repo.findByListIdAndUsername(listId, username);
		lm.apply(list);
		repo.save(lm);
	}
	
	public void deleteList(String username, String listId) {
		ListModel lm = repo.findByListIdAndUsername(listId, username);
		repo.delete(lm);
	}

}
