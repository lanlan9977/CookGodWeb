package com.broadcast.model;

import java.sql.Timestamp;

public class BroadcastVO implements java.io.Serializable {
	private String broadcastId;
	private Timestamp broadcastStart;
	private String broadcastCon;
	private String broadcastStatus;
	private String custId;

	public BroadcastVO() {
		super();
	}

	public BroadcastVO(String broadcastId, Timestamp broadcastStart, String broadcastCon, String broadcastStatus,
			String custId) {
		super();
		this.broadcastId = broadcastId;
		this.broadcastStart = broadcastStart;
		this.broadcastCon = broadcastCon;
		this.broadcastStatus = broadcastStatus;
		this.custId = custId;
	}

	public String getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(String broadcastId) {
		this.broadcastId = broadcastId;
	}

	public Timestamp getBroadcastStart() {
		return broadcastStart;
	}

	public void setBroadcastStart(Timestamp broadcastStart) {
		this.broadcastStart = broadcastStart;
	}

	public String getBroadcastCon() {
		return broadcastCon;
	}

	public void setBroadcastCon(String broadcastCon) {
		this.broadcastCon = broadcastCon;
	}

	public String getBroadcastStatus() {
		return broadcastStatus;
	}

	public void setBroadcastStatus(String broadcastStatus) {
		this.broadcastStatus = broadcastStatus;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

}
