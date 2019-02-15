package com.chefOrder.model;

import java.util.*;

public interface ChefOrderDAO_interface {
	public void insert(ChefOrderVO chefOrderVO);

	public void update(ChefOrderVO chefOrderVO);

	public void delete(String chef_or_ID);

	public ChefOrderVO findByPrimaryKey(String chef_or_ID);

	public List<ChefOrderVO> getAll();
}
