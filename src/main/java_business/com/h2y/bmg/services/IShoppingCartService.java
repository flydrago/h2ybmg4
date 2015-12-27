package com.h2y.bmg.services;

import java.util.List;
import java.util.Map;

public interface IShoppingCartService {
	public List<Map<String, Object>> queryCart(Map<String, Object> map);
}
