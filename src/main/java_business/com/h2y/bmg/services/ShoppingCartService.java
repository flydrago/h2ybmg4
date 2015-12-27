package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.IShoppingCartDao;
import com.h2y.util.JSONUtil;


@Service("shoppingCartService")
public class ShoppingCartService implements IShoppingCartService{
	
	@Autowired
	protected IShoppingCartDao shoppingCartDao;

	//查询我的购物车
	public List<Map<String, Object>> queryCart(Map<String, Object> paraMap){
		List<Map<String, Object>>  cartList=shoppingCartDao.queryCart(paraMap);
		return cartList;
	}
}
