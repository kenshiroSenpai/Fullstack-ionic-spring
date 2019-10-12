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

import com.kenshiro.lists.entity.models.Product;
import com.kenshiro.lists.entity.services.IProductsService;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {

	@Autowired
	private IProductsService productService;

	@GetMapping("/search/product/{id}")
	private Product getProductById(@PathVariable(value = "id") long id) {
		return productService.findOneProduct(id);
	}

	@GetMapping("/search/products")
	private List<Product> getProducts() {
		return productService.findAll();
	}

	@GetMapping("/search/list/products/{id}")
	private List<Product> getProductsInList(@PathVariable(value = "id") long id) {
		return productService.findAllProductsInList(id);
	}
	
	@PostMapping("/save/products/{idProduct}/list/{idList}")
	private void setProductsExistInList(@PathVariable(value="idProduct") long idProduct, @PathVariable(value="idList") long idList) {
		productService.saveProductExistInList(idProduct, idList);
	}
	
	@PostMapping("/save/product/list/{id}")
	private void setProductsInList(Product product, @PathVariable(value="id") long id) {
		productService.saveProductsInList(product, id);
	}

	@PostMapping("/save/product")
	private void setProduct(Product product) {
		productService.saveProduct(product);
	}
	
	@PutMapping("/modify/product/{id}")
	private void updateProduct(Product product, @PathVariable(value="id") long id) {
		productService.updateProduct(product, id);
	}
	
	@DeleteMapping("/delete/product/{id}")
	private void deleteProduct(@PathVariable(value="id") long id) {
		productService.delete(id);
	}
	
	@DeleteMapping("/delete/{idList}/{idProduct}")
	private void deleteProductInList(@PathVariable(value="idProduct") long idProduct, @PathVariable(value="idList") long idList) {
		productService.deleteProductInList(idProduct, idList);
	}

}
