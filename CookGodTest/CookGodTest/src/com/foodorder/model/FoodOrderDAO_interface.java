package com.foodorder.model;

import java.util.List;


public interface FoodOrderDAO_interface {
	void insert(FoodOrderVO foodOrderVO);
	void update(FoodOrderVO foodOrderVO);
	void delete(String food_or_ID);
	FoodOrderVO findByPrimaryKey(String food_or_ID);
	List<FoodOrderVO> getAll();
}
