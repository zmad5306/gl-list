package com.example.gl.list.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.gl.list.model.ListModel;

public interface ListRepository extends MongoRepository<ListModel, String> {
	
	public List<ListModel> findByUsername(String username);
	public ListModel findByListIdAndUsername(String id, String username);

}
