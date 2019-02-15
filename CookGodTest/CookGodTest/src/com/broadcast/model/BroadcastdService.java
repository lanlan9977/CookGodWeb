package com.broadcast.model;

import java.sql.Timestamp;

public class BroadcastdService {
	private BroadcastDAO_interface dao;

	public BroadcastdService() {
		dao = new BroadcastDAO();
	}

	public BroadcastVO addbroadcast(String broadcastId, Timestamp broadcastStart, String broadcastCon,
			String broadcastStatus, String custId) {
		BroadcastVO broadcastVO = new BroadcastVO();
		
		
		broadcastVO.setBroadcastId(broadcastId);
		broadcastVO.setBroadcastStart(broadcastStart);
		broadcastVO.setBroadcastCon(broadcastCon);
		broadcastVO.setBroadcastStatus(broadcastStatus);
		broadcastVO.setCustId(custId);
		dao.insert(broadcastVO);
		
		
		return broadcastVO;
	}

}
