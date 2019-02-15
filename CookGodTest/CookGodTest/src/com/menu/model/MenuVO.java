package com.menu.model;

public class MenuVO implements java.io.Serializable {
	
	private String menuId;
	private String menuName;
	private String menuResume;
	private byte[] menuPic;
	private String menuStatus;
	private Integer menuPrice;
	
	public MenuVO() {
		super();
	}
	
	public MenuVO(String menuId, String menuName, String menuResume, byte[] menuPic, String menuStatus,
			Integer menuPrice) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuResume = menuResume;
		this.menuPic = menuPic;
		this.menuStatus = menuStatus;
		this.menuPrice = menuPrice;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuResume() {
		return menuResume;
	}

	public void setMenuResume(String menuResume) {
		this.menuResume = menuResume;
	}

	public byte[] getMenuPic() {
		return menuPic;
	}

	public void setMenuPic(byte[] menuPic) {
		this.menuPic = menuPic;
	}

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}

	public Integer getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(Integer menuPrice) {
		this.menuPrice = menuPrice;
	}
}
