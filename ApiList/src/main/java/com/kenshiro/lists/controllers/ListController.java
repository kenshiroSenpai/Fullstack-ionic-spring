package com.kenshiro.lists.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenshiro.lists.entity.models.ListApp;
import com.kenshiro.lists.entity.services.IListService;

@CrossOrigin(origins = "*")
@RestController
public class ListController {
	
	@Autowired
	private IListService listService;
	
	@GetMapping("/search/list/{id}")
	private ListApp getListById(@PathVariable(value= "id") long id) {
		return listService.findOneList(id);
	}
	
	@GetMapping("/search/all/lists")
	private List<ListApp> getLists(){
		return listService.findAll();
	}
	
	@PostMapping("/save/list")
	private void setList(ListApp list) {
		listService.saveList(list);
	}
	
	@DeleteMapping("/delete/list/{id}")
	private void deleteList(@PathVariable(value = "id") long id) {
		listService.delete(id);
	}
	
	@PutMapping("/modify/list/{id}")
	private void updateList(ListApp list, @PathVariable(value="id") long id) {
		listService.updateList(list, id);
	}

}
