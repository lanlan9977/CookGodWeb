package com.broadcast.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BroadcastDAO implements BroadcastDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO BROADCAST(BROADCAST_ID,BROADCAST_START,BROADCAST_CON,BROADCAST_STATUS,CUST_ID)  VALUES ('B'||LPAD((BROADCAST_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM BROADCAST";
	private static final String GET_ONE_STMT = "SELECT * FROM BROADCAST WHERE BROADCAST_ID = ?";
	private static final String DELETE = "DELETE FROM BROADCAST WHERE BROADCAST_ID = ?";
	private static final String UPDATE = "UPDATE BROADCAST SET BROADCAST_START= ?, BROADCAST_CON= ?, BROADCAST_STATUS= ?, CUST_ID= ? WHERE BROADCAST_ID = ?";

	@Override
	public void insert(BroadcastVO broadcastVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setTimestamp(1, broadcastVO.getBroadcastStart());
			pstmt.setString(2, broadcastVO.getBroadcastCon());
			pstmt.setString(3, broadcastVO.getBroadcastStatus());
			pstmt.setString(4, broadcastVO.getCustId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(BroadcastVO broadcastVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, broadcastVO.getBroadcastStart());
			pstmt.setString(2, broadcastVO.getBroadcastCon());
			pstmt.setString(3, broadcastVO.getBroadcastStatus());
			pstmt.setString(4, broadcastVO.getCustId());
			pstmt.setString(5, broadcastVO.getBroadcastId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String broadcastId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, broadcastId);
			pstmt.executeUpdate();

			con.commit();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public BroadcastVO findByPrimaryKey(String broadcastId) {
		BroadcastVO broadcastVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, broadcastId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				broadcastVO = new BroadcastVO();
				broadcastVO.setBroadcastId(rs.getString("BROADCAST_ID"));
				broadcastVO.setBroadcastStart(rs.getTimestamp("BROADCAST_START"));
				broadcastVO.setBroadcastCon(rs.getString("BROADCAST_CON"));
				broadcastVO.setBroadcastStatus(rs.getString("BROADCAST_STATUS"));
				broadcastVO.setCustId(rs.getString("CUST_ID"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return broadcastVO;
	}

	@Override
	public List<BroadcastVO> getAll() {
		List<BroadcastVO> list = new ArrayList<BroadcastVO>();
		BroadcastVO broadcastVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				broadcastVO = new BroadcastVO();
				broadcastVO.setBroadcastId(rs.getString("BROADCAST_ID"));
				broadcastVO.setBroadcastStart(rs.getTimestamp("BROADCAST_START"));
				broadcastVO.setBroadcastCon(rs.getString("BROADCAST_CON"));
				broadcastVO.setBroadcastStatus(rs.getString("BROADCAST_STATUS"));
				broadcastVO.setCustId(rs.getString("CUST_ID"));
				list.add(broadcastVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
