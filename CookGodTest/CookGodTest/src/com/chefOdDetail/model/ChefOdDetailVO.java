package com.chefOdDetail.model;

public class ChefOdDetailVO {
	private String chefOrId;
	private String foodSupId;
	private String foodId;
	private Integer chefOdQty;
	private Integer chefOdStotal;

	public ChefOdDetailVO() {
		super();
	}

	public ChefOdDetailVO(String chefOrId, String foodSupId, String foodId, Integer chefOdQty, Integer chefOdStotal) {
		super();
		this.chefOrId = chefOrId;
		this.foodSupId = foodSupId;
		this.foodId = foodId;
		this.chefOdQty = chefOdQty;
		this.chefOdStotal = chefOdStotal;
	}

	public String getChefOrId() {
		return chefOrId;
	}

	public void setChefOrId(String chefOrId) {
		this.chefOrId = chefOrId;
	}

	public String getFoodSupId() {
		return foodSupId;
	}

	public void setFoodSupId(String foodSupId) {
		this.foodSupId = foodSupId;
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public Integer getChefOdQty() {
		return chefOdQty;
	}

	public void setChefOdQty(Integer chefOdQty) {
		this.chefOdQty = chefOdQty;
	}

	public Integer getChefOdStotal() {
		return chefOdStotal;
	}

	public void setChefOdStotal(Integer chefOdStotal) {
		this.chefOdStotal = chefOdStotal;
	}

}
