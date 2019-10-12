package com.kenshiro.lists.entity.services;

import java.util.List;
import com.kenshiro.lists.entity.models.Product;

public interface IProductsService {
	
	public List<Product> findAll();
	public void delete(long id);
	public void updateProduct(Product list, long id);
	public void saveProduct(Product product);
	public Product findOneProduct(long idProduct);
	public List<Product> findAllProductsInList(long id);
	public void saveProductExistInList(long idProduct, long idList);
	public void saveProductsInList(Product product, long idList);
	public void deleteProductInList(long idProduct, long idList);

}
