package com.kenshiro.lists.entity.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "lists")
public class ListApp implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_list")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idList;

	@ManyToMany
	@JoinTable(name = "lists_has_products", joinColumns = @JoinColumn(name = "id_list"), inverseJoinColumns = @JoinColumn(name = "id_product"))
	private Set<Product> belongedProducts = new HashSet<Product>();

	@Column(name = "list_name")
	@NotEmpty
	private String listName;

	public ListApp() {
	}

	public ListApp(long idList, Set<Product> belongedProducts, String listName) {
		super();
		this.idList = idList;
		this.belongedProducts = belongedProducts;
		this.listName = listName;
	}

	public long getIdList() {
		return idList;
	}

	public void setIdList(long idList) {
		this.idList = idList;
	}

	public Set<Product> getBelongedProducts() {
		return belongedProducts;
	}

	public void setBelongedProducts(Set<Product> belongedProducts) {
		this.belongedProducts = belongedProducts;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

}
