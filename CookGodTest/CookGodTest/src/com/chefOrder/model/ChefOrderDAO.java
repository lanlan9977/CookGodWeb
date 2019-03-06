package com.chefOrder.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ChefOrderDAO implements ChefOrderDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CookGodDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO CHEF_ORDER(CHEF_OR_ID,CHEF_OR_STATUS,CHEF_OR_START,CHEF_OR_SEND,CHEF_OR_RCV,CHEF_OR_END,CHEF_OR_NAME,CHEF_OR_ADDR,CHEF_OR_TEL,CHEF_ID) VALUES ('CF'||TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD((CHEF_ORDER_SEQ.NEXTVAL),6,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM CHEF_ORDER";
	private static final String GET_ONE_STMT = "SELECT * FROM CHEF_ORDER WHERE CHEF_OR_ID = ?";
	private static final String DELETE = "DELETE FROM CHEF_ORDER WHERE CHEF_OR_ID = ?";
	private static final String UPDATE = "UPDATE CHEF_ORDER SET CHEF_OR_STATUS= ?, CHEF_OR_START= ?, CHEF_OR_SEND= ?, CHEF_OR_RCV= ?, CHEF_OR_END= ?, CHEF_OR_NAME= ?, CHEF_OR_ADDR= ?, CHEF_OR_TEL= ?, CHEF_ID= ? WHERE CHEF_OR_ID = ?";

	@Override
	public void insert(ChefOrderVO chefOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chefOrderVO.getChef_or_status());
			pstmt.setTimestamp(2, chefOrderVO.getChef_or_start());
			pstmt.setTimestamp(3, chefOrderVO.getChef_or_send());
			pstmt.setTimestamp(4, chefOrderVO.getChef_or_rcv());
			pstmt.setTimestamp(5, chefOrderVO.getChef_or_end());
			pstmt.setString(6, chefOrderVO.getChef_or_name());
			pstmt.setString(7, chefOrderVO.getChef_or_addr());
			pstmt.setString(8, chefOrderVO.getChef_or_tel());
			pstmt.setString(9, chefOrderVO.getChef_ID());

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
	public void update(ChefOrderVO chefOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chefOrderVO.getChef_or_status());
			pstmt.setTimestamp(2, chefOrderVO.getChef_or_start());
			pstmt.setTimestamp(3, chefOrderVO.getChef_or_send());
			pstmt.setTimestamp(4, chefOrderVO.getChef_or_rcv());
			pstmt.setTimestamp(5, chefOrderVO.getChef_or_end());
			pstmt.setString(6, chefOrderVO.getChef_or_name());
			pstmt.setString(7, chefOrderVO.getChef_or_addr());
			pstmt.setString(8, chefOrderVO.getChef_or_tel());
			pstmt.setString(9, chefOrderVO.getChef_ID());
			pstmt.setString(10, chefOrderVO.getChef_or_ID());

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
	public void delete(String chef_or_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, chef_or_ID);
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
	public ChefOrderVO findByPrimaryKey(String chef_or_ID) {
		ChefOrderVO chefOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chef_or_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefOrderVO = new ChefOrderVO();
				chefOrderVO.setChef_or_ID(rs.getString("CHEF_OR_ID"));
				chefOrderVO.setChef_or_status(rs.getString("CHEF_OR_STATUS"));
				chefOrderVO.setChef_or_start(rs.getTimestamp("CHEF_OR_START"));
				chefOrderVO.setChef_or_end(rs.getTimestamp("CHEF_OR_SEND"));
				chefOrderVO.setChef_or_rcv(rs.getTimestamp("CHEF_OR_RCV"));
				chefOrderVO.setChef_or_end(rs.getTimestamp("CHEF_OR_END"));
				chefOrderVO.setChef_or_name(rs.getString("CHEF_OR_NAME"));
				chefOrderVO.setChef_or_addr(rs.getString("CHEF_OR_ADDR"));
				chefOrderVO.setChef_or_tel(rs.getString("CHEF_OR_TEL"));
				chefOrderVO.setChef_ID(rs.getString("CHEF_ID"));
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
		return chefOrderVO;
	}

	@Override
	public List<ChefOrderVO> getAll() {
		List<ChefOrderVO> list = new ArrayList<ChefOrderVO>();
		ChefOrderVO chefOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefOrderVO = new ChefOrderVO();
				chefOrderVO.setChef_or_ID(rs.getString("CHEF_OR_ID"));
				chefOrderVO.setChef_or_status(rs.getString("CHEF_OR_STATUS"));
				chefOrderVO.setChef_or_start(rs.getTimestamp("CHEF_OR_START"));
				chefOrderVO.setChef_or_end(rs.getTimestamp("CHEF_OR_SEND"));
				chefOrderVO.setChef_or_rcv(rs.getTimestamp("CHEF_OR_RCV"));
				chefOrderVO.setChef_or_end(rs.getTimestamp("CHEF_OR_END"));
				chefOrderVO.setChef_or_name(rs.getString("CHEF_OR_NAME"));
				chefOrderVO.setChef_or_addr(rs.getString("CHEF_OR_ADDR"));
				chefOrderVO.setChef_or_tel(rs.getString("CHEF_OR_TEL"));
				chefOrderVO.setChef_ID(rs.getString("CHEF_ID"));
				list.add(chefOrderVO);
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
