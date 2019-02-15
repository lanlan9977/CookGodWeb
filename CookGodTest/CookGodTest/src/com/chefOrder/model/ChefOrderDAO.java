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
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
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

			pstmt.setString(1, chefOrderVO.getChefOrStatus());
			pstmt.setDate(2, chefOrderVO.getChefOrStart());
			pstmt.setDate(3, chefOrderVO.getChefOrSend());
			pstmt.setDate(4, chefOrderVO.getChefOrRcv());
			pstmt.setDate(5, chefOrderVO.getChefOrEnd());
			pstmt.setString(6, chefOrderVO.getChefOrName());
			pstmt.setString(7, chefOrderVO.getChefOrAddr());
			pstmt.setInt(8, chefOrderVO.getChefOrTel());
			pstmt.setString(9, chefOrderVO.getChefId());

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

			pstmt.setString(1, chefOrderVO.getChefOrStatus());
			pstmt.setDate(2, chefOrderVO.getChefOrStart());
			pstmt.setDate(3, chefOrderVO.getChefOrSend());
			pstmt.setDate(4, chefOrderVO.getChefOrRcv());
			pstmt.setDate(5, chefOrderVO.getChefOrEnd());
			pstmt.setString(6, chefOrderVO.getChefOrName());
			pstmt.setString(7, chefOrderVO.getChefOrAddr());
			pstmt.setInt(8, chefOrderVO.getChefOrTel());
			pstmt.setString(9, chefOrderVO.getChefId());
			pstmt.setString(10, chefOrderVO.getChefOrId());

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
	public void delete(String chefOrId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, chefOrId);
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
	public ChefOrderVO findByPrimaryKey(String chefOrId) {
		ChefOrderVO chefOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chefOrId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				chefOrderVO = new ChefOrderVO();
				chefOrderVO.setChefOrId(rs.getString("CHEF_OR_ID"));
				chefOrderVO.setChefOrStatus(rs.getString("CHEF_OR_STATUS"));
				chefOrderVO.setChefOrStart(rs.getDate("CHEF_OR_START"));
				chefOrderVO.setChefOrEnd(rs.getDate("CHEF_OR_SEND"));
				chefOrderVO.setChefOrRcv(rs.getDate("CHEF_OR_RCV"));
				chefOrderVO.setChefOrEnd(rs.getDate("CHEF_OR_END"));
				chefOrderVO.setChefOrName(rs.getString("CHEF_OR_NAME"));
				chefOrderVO.setChefOrAddr(rs.getString("CHEF_OR_ADDR"));
				chefOrderVO.setChefOrTel(rs.getInt("CHEF_OR_TEL"));
				chefOrderVO.setChefId(rs.getString("CHEF_ID"));
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
				chefOrderVO.setChefOrId(rs.getString("CHEF_OR_ID"));
				chefOrderVO.setChefOrStatus(rs.getString("CHEF_OR_STATUS"));
				chefOrderVO.setChefOrStart(rs.getDate("CHEF_OR_START"));
				chefOrderVO.setChefOrEnd(rs.getDate("CHEF_OR_SEND"));
				chefOrderVO.setChefOrRcv(rs.getDate("CHEF_OR_RCV"));
				chefOrderVO.setChefOrEnd(rs.getDate("CHEF_OR_END"));
				chefOrderVO.setChefOrName(rs.getString("CHEF_OR_NAME"));
				chefOrderVO.setChefOrAddr(rs.getString("CHEF_OR_ADDR"));
				chefOrderVO.setChefOrTel(rs.getInt("CHEF_OR_TEL"));
				chefOrderVO.setChefId(rs.getString("CHEF_ID"));
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
