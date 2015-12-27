package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import com.h2y.bmg.entity.ShoppingCart;

public interface IShoppingCartDao {
	public List<Map<String, Object>> queryCart(Map<String, Object> map);
	
	public ShoppingCart get(long id);

}
