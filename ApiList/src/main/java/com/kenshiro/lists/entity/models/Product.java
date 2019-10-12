package com.kenshiro.lists.entity.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_product")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProduct;

	@JsonIgnore
	@ManyToMany(mappedBy = "belongedProducts")
	private Set<ListApp> belong = new HashSet<ListApp>();

	@Column(name = "product_name")
	@NotEmpty
	private String productName;

	public Product() {
	}

	public Product(long idProduct, Set<ListApp> belong, @NotEmpty String productName) {
		super();
		this.idProduct = idProduct;
		this.belong = belong;
		this.productName = productName;
	}

	public long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}

	public Set<ListApp> getBelong() {
		return belong;
	}

	public void setBelong(Set<ListApp> belong) {
		this.belong = belong;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
