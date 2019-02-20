package com.broadcast.model;

import java.util.List;
import java.util.Vector;
import java.sql.Timestamp;

public class BroadcastService {
	private BroadcastDAO_interface dao;

	public BroadcastService() {
		dao = new BroadcastDAO();
	}

	public BroadcastVO addBroadcast(String broadcast_con, String cust_ID) {
		BroadcastVO broadcastVO = new BroadcastVO();
		broadcastVO.setBroadcast_start(new Timestamp(System.currentTimeMillis()));
		broadcastVO.setBroadcast_con(broadcast_con);
		broadcastVO.setBroadcast_status("B0");
		broadcastVO.setCust_ID(cust_ID);
		dao.insert(broadcastVO);

		return broadcastVO;
	}

	public BroadcastVO updateBroadcast(String broadcast_ID, String broadcast_status) {
		BroadcastVO broadcastVO = new BroadcastVO();
		broadcastVO.setBroadcast_ID(broadcast_ID);
		broadcastVO.setBroadcast_status(broadcast_status);
	
		dao.update(broadcastVO);

		return broadcastVO;
	}

	public void daleteBroadcast(String broadcast_ID) {
		dao.delete(broadcast_ID);
	}

	public BroadcastVO getOneBroadcast(String broadcast_ID) {
		return dao.findByPrimaryKey(broadcast_ID);
	}

	public List<BroadcastVO> gelAllBroadcast() {
		return dao.getAll();
	}

	public List<BroadcastVO> getOneBroadcastByCustID(String cust_ID) {
		return dao.findByCust_ID(cust_ID);
	}

}
