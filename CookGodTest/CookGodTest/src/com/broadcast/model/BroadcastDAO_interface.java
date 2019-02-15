package com.broadcast.model;

import java.util.*;

public interface BroadcastDAO_interface {
	public void insert(BroadcastVO broadcastVO);

	public void update(BroadcastVO broadcastVO);

	public void delete(String broadcastId);

	public BroadcastVO findByPrimaryKey(String broadcastId);

	public List<BroadcastVO> getAll();
}
