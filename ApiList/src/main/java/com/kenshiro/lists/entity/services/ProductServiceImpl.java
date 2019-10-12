package com.kenshiro.lists.entity.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kenshiro.lists.entity.dao.IListDao;
import com.kenshiro.lists.entity.dao.IProductDao;
import com.kenshiro.lists.entity.models.Product;

@Service
public class ProductServiceImpl implements IProductsService {

	@Autowired
	private IProductDao productDao;

	@Autowired
	private IListDao listDao;

	@Override
	public List<Product> findAll() {
		return (List<Product>) productDao.findAll();
	}

	@Override
	public void delete(long id) {
		productDao.deleteById(id);

	}

	@Override
	public void updateProduct(Product product, long id) {
		productDao.findById(id).ifPresent(res -> {
			product.setIdProduct(id);
			productDao.save(product);
		});

	}

	@Override
	public void saveProduct(Product product) {
		try {
			if (product.getProductName().isEmpty()) {
				throw new NullPointerException("rellene los campos vacios.");
			}
			productDao.save(product);
		} catch (NullPointerException e) {
			System.out.println("rellene los campos vacios.");
		}
		

	}

	@Override
	public Product findOneProduct(long idProduct) {
		return productDao.findById(idProduct).get();
	}

	@Override
	public List<Product> findAllProductsInList(long id) {
		return listDao.findAllProductsInList(id);
	}

	@Override
	public void saveProductExistInList(long idProduct, long idList) {
		try {
			productDao.findById(idProduct).ifPresent((productFound) -> {
				listDao.findById(idList).ifPresent((listFound) -> {
					listFound.getBelongedProducts().add(productFound);
					listDao.save(listFound);
				});
			});
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void saveProductsInList(Product product, long idList) {
		try {
			if (product.getProductName().isEmpty()) {
				throw new NullPointerException("rellene los campos vacios.");
			}
			Product newPro = productDao.save(product);
			listDao.findById(idList).ifPresent((listFound) -> {
				listFound.getBelongedProducts().add(newPro);
				listDao.save(listFound);
			});
		} catch (NullPointerException e) {
			System.out.println("rellene los campos vacios.");

		}

	}

	@Transactional
	@Override
	public void deleteProductInList(long idProduct, long idList) {
		try {
			productDao.findById(idProduct).ifPresent((productFound) -> {
				listDao.findById(idList).ifPresent((listFound) -> {
					listFound.getBelongedProducts().remove(productFound);
					listDao.save(listFound);
				});
			});
		} catch (Exception e) {
			System.out.println(e);
			// String res = "{" + "message=" + e + "}";
		}

	}

}
