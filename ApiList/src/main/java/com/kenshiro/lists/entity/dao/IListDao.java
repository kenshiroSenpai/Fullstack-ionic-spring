package com.kenshiro.lists.entity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kenshiro.lists.entity.models.ListApp;
import com.kenshiro.lists.entity.models.Product;

public interface IListDao extends CrudRepository<ListApp, Long>{
	
	@Query("SELECT s.belongedProducts FROM ListApp s WHERE s.idList = :id")
	public List<Product> findAllProductsInList(@Param("id") long id);
}
