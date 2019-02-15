package com.chefOrder.model;

import java.util.*;

public interface ChefOrderDAO_interface {
	public void insert(ChefOrderVO chefOrderVO);

	public void update(ChefOrderVO chefOrderVO);

	public void delete(String chefOrId);

	public ChefOrderVO findByPrimaryKey(String chefOrId);

	public List<ChefOrderVO> getAll();
}
