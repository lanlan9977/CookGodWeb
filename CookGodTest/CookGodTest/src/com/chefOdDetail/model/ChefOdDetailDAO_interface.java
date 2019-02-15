package com.chefOdDetail.model;

import java.util.*;

public interface ChefOdDetailDAO_interface {
	public void insert(ChefOdDetailVO chefOdDetailVO);

	public void update(ChefOdDetailVO chefOdDetailVO);

	public void delete(String chefOrId);

	public ChefOdDetailVO findByPrimaryKey(String chefOrId);

	public List<ChefOdDetailVO> getAll();
}
