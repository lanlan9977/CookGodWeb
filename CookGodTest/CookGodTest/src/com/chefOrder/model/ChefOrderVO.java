package com.chefOrder.model;

import java.sql.Date;

public class ChefOrderVO {
	private String chefOrId;
	private String chefOrStatus;
	private Date chefOrStart;
	private Date chefOrSend;
	private Date chefOrRcv;
	private Date chefOrEnd;
	private String chefOrName;
	private String chefOrAddr;
	private Integer chefOrTel;
	private String chefId;

	public ChefOrderVO() {
		super();
	}

	public ChefOrderVO(String chefOrId, String chefOrStatus, Date chefOrStart, Date chefOrSend, Date chefOrRcv,
			Date chefOrEnd, String chefOrName, String chefOrAddr, Integer chefOrTel, String chefId) {
		super();
		this.chefOrId = chefOrId;
		this.chefOrStatus = chefOrStatus;
		this.chefOrStart = chefOrStart;
		this.chefOrSend = chefOrSend;
		this.chefOrRcv = chefOrRcv;
		this.chefOrEnd = chefOrEnd;
		this.chefOrName = chefOrName;
		this.chefOrAddr = chefOrAddr;
		this.chefOrTel = chefOrTel;
		this.chefId = chefId;
	}

	public String getChefOrId() {
		return chefOrId;
	}

	public void setChefOrId(String chefOrId) {
		this.chefOrId = chefOrId;
	}

	public String getChefOrStatus() {
		return chefOrStatus;
	}

	public void setChefOrStatus(String chefOrStatus) {
		this.chefOrStatus = chefOrStatus;
	}

	public Date getChefOrStart() {
		return chefOrStart;
	}

	public void setChefOrStart(Date chefOrStart) {
		this.chefOrStart = chefOrStart;
	}

	public Date getChefOrSend() {
		return chefOrSend;
	}

	public void setChefOrSend(Date chefOrSend) {
		this.chefOrSend = chefOrSend;
	}

	public Date getChefOrRcv() {
		return chefOrRcv;
	}

	public void setChefOrRcv(Date chefOrRcv) {
		this.chefOrRcv = chefOrRcv;
	}

	public Date getChefOrEnd() {
		return chefOrEnd;
	}

	public void setChefOrEnd(Date chefOrEnd) {
		this.chefOrEnd = chefOrEnd;
	}

	public String getChefOrName() {
		return chefOrName;
	}

	public void setChefOrName(String chefOrName) {
		this.chefOrName = chefOrName;
	}

	public String getChefOrAddr() {
		return chefOrAddr;
	}

	public void setChefOrAddr(String chefOrAddr) {
		this.chefOrAddr = chefOrAddr;
	}

	public Integer getChefOrTel() {
		return chefOrTel;
	}

	public void setChefOrTel(Integer chefOrTel) {
		this.chefOrTel = chefOrTel;
	}

	public String getChefId() {
		return chefId;
	}

	public void setChefId(String chefId) {
		this.chefId = chefId;
	}
}
