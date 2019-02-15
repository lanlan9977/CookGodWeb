package com.favChef.model;

import java.util.*;

public interface FavChefDAO_interface {

	public void insert(FavChefVO favChefVO);

	public void update(FavChefVO favChefVO);

	public void delete(String custId);

	public FavChefVO findByPrimaryKey(String custId);

	public List<FavChefVO> getAll();
}
