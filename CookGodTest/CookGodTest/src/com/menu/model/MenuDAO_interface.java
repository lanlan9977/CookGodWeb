package com.menu.model;

import java.util.*;

public interface MenuDAO_interface {
	public void insert(MenuVO menuVO);

	public void update(MenuVO menuVO);

	public void delete(String menuId);

	public MenuVO findByPrimaryKey(String menuId);

	public List<MenuVO> getAll();
}
