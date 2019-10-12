package com.kenshiro.lists.entity.services;

import java.util.List;
import com.kenshiro.lists.entity.models.ListApp;

public interface IListService {
	
	public List<ListApp> findAll();
	public void delete(long id);
	public ListApp findOneList(long idList);
	public void saveList(ListApp list);
	public void saveListIdWithProductId(long idList, long idProducts);
	public void updateList(ListApp list, long id);

}
