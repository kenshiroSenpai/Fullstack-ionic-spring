package com.kenshiro.lists.entity.dao;

import org.springframework.data.repository.CrudRepository;
import com.kenshiro.lists.entity.models.Product;

public interface IProductDao extends CrudRepository<Product, Long>{
	

}
