package com.kenshiro.lists.entity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenshiro.lists.entity.dao.IListDao;
//import com.kenshiro.lists.entity.dao.IProductDao;
import com.kenshiro.lists.entity.models.ListApp;

@Service
public class ListServiceImpl implements IListService{
	
	@Autowired
	private IListDao listDao;

	@Override
	public List<ListApp> findAll() {
		return (List<ListApp>)listDao.findAll();
	}

	@Override
	public void delete(long id) {
		listDao.deleteById(id);
		
	}

	@Override
	public ListApp findOneList(long idList) {
		return listDao.findById(idList).get();
	}

	@Override
	public void saveList(ListApp list) {
		try {
			if (list.getListName().isEmpty()) {
				throw new NullPointerException("rellene los campos vacios.");
			}
			listDao.save(list);
		} catch (NullPointerException e) {
			System.out.println("rellene los campos vacios.");
		}
		
		
	}

	@Override
	public void saveListIdWithProductId(long idList, long idProducts) {}

	@Override
	public void updateList(ListApp list, long id) {
		listDao.findById(id).ifPresent(res ->{
			res.setListName(list.getListName());
			listDao.save(res);
		});
	}

}
