package android.com.festOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FestOrderJDBCDAO implements FestOrder_Interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "COOKGOD";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = "INSERT INTO FEST_ORDER (FEST_OR_ID, FEST_OR_STATUS,FEST_OR_PRICE,FEST_OR_START,FEST_OR_SEND,FEST_OR_END,FEST_OR_DISC,CUST_ID) VALUES (FEST_ORDER_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE FEST_ORDER SET FEST_OR_STATUS = ?,FEST_OR_PRICE = ?,FEST_OR_START = ?,FEST_OR_SEND = ?,FEST_OR_END = ?,FEST_OR_DISC = ?, CUST_ID = ? WHERE FEST_OR_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM FEST_ORDER WHERE FEST_OR_ID = ?";
	private static final String GET_ONE_STMT_CUSTID = "SELECT * FROM FEST_ORDER WHERE CUST_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM FEST_ORDER";
	private static final String GET_ONE_STMT = "SELECT * FROM FEST_ORDER WHERE FEST_OR_ID = ?";

	@Override
	public void insert(FestOrderVO festOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, festOrderVO.getFest_or_status());
			pstmt.setInt(2, festOrderVO.getFest_or_price());
			pstmt.setDate(3, festOrderVO.getFest_or_start());
			pstmt.setDate(4, festOrderVO.getFest_or_send());
			pstmt.setDate(5, festOrderVO.getFest_or_end());
			pstmt.setString(6, festOrderVO.getFest_or_disc());
			pstmt.setString(7, festOrderVO.getCust_ID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(FestOrderVO festOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, festOrderVO.getFest_or_status());
			pstmt.setInt(2, festOrderVO.getFest_or_price());
			pstmt.setDate(3, festOrderVO.getFest_or_start());
			pstmt.setDate(4, festOrderVO.getFest_or_send());
			pstmt.setDate(5, festOrderVO.getFest_or_end());
			pstmt.setString(6, festOrderVO.getFest_or_disc());
			pstmt.setString(7, festOrderVO.getCust_ID());
			pstmt.setString(8, festOrderVO.getFest_or_ID());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(String fest_or_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, fest_or_ID);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public FestOrderVO findByPrimaryKey(String fest_or_ID) {

		FestOrderVO festOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fest_or_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				festOrderVO = new FestOrderVO();

				festOrderVO.setFest_or_ID(rs.getString("fest_or_ID"));
				festOrderVO.setFest_or_status(rs.getString("fest_or_status"));
				festOrderVO.setFest_or_price(rs.getInt("fest_or_price"));
				festOrderVO.setFest_or_start(rs.getDate("fest_or_start"));
				festOrderVO.setFest_or_send(rs.getDate("fest_or_send"));
				festOrderVO.setFest_or_end(rs.getDate("fest_or_end"));
				festOrderVO.setFest_or_disc(rs.getString("fest_or_disc"));
				festOrderVO.setCust_ID(rs.getString("cust_ID"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return festOrderVO;
	}

	@Override
	public List<FestOrderVO> getAll() {
		List<FestOrderVO> list = new ArrayList<FestOrderVO>();
		FestOrderVO festOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				festOrderVO = new FestOrderVO();

				festOrderVO.setFest_or_ID(rs.getString("fest_or_ID"));
				festOrderVO.setFest_or_status(rs.getString("fest_or_status"));
				festOrderVO.setFest_or_price(rs.getInt("fest_or_price"));
				festOrderVO.setFest_or_start(rs.getDate("fest_or_start"));
				festOrderVO.setFest_or_send(rs.getDate("fest_or_send"));
				festOrderVO.setFest_or_end(rs.getDate("fest_or_end"));
				festOrderVO.setFest_or_disc(rs.getString("fest_or_disc"));
				festOrderVO.setCust_ID(rs.getString("cust_ID"));
				list.add(festOrderVO);

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {

		FestOrderJDBCDAO dao = new FestOrderJDBCDAO();
		Calendar currentTime = Calendar.getInstance();
		java.sql.Date sqlDate = new java.sql.Date(currentTime.getTimeInMillis());
		// 新增

//		FestOrderVO festOrderVO = new FestOrderVO();
//		festOrderVO.setFest_or_status("3");
//		festOrderVO.setFest_or_price(2800);
//		festOrderVO.setFest_or_start(sqlDate);
//		festOrderVO.setFest_or_send(sqlDate);
//		festOrderVO.setFest_or_end(sqlDate);
//		festOrderVO.setFest_or_disc("0.8");
//		festOrderVO.setCust_ID("C00001");
//		dao.insert(festOrderVO);

		// 修改

//		FestOrderVO festOrderVO = new FestOrderVO();
//		festOrderVO.setFest_or_status("4");
//		festOrderVO.setFest_or_price(2800);
//		festOrderVO.setFest_or_start(sqlDate);
//		festOrderVO.setFest_or_send(sqlDate);
//		festOrderVO.setFest_or_end(sqlDate);
//		festOrderVO.setFest_or_disc("0.8");
//		festOrderVO.setCust_ID("C00001");
//		festOrderVO.setFest_or_ID("3");
//		dao.update(festOrderVO);

		// 刪除
//		dao.delete("1");

		// 查詢

//		FestOrderVO festOrderVO=dao.findByPrimaryKey("2");
//		System.out.println(festOrderVO.getFest_or_ID()+ ",");
//		System.out.println(festOrderVO.getFest_or_status()+ ",");
//		System.out.println(festOrderVO.getFest_or_price() + ",");
//		System.out.println(festOrderVO.getFest_or_start() + ",");
//		System.out.println(festOrderVO.getFest_or_send() + ",");
//		System.out.println(festOrderVO.getFest_or_end() + ",");
//		System.out.println(festOrderVO.getFest_or_disc() + ",");
//		System.out.println(festOrderVO.getCust_ID() + ",");

		// 查詢全部
//		List<FestOrderVO> list = dao.getAll();
//            for (FestOrderVO aFestOrder : list) {
//
//			System.out.println(aFestOrder.getFest_or_ID() + ",");
//			System.out.println(aFestOrder.getFest_or_status() + ",");
//			System.out.println(aFestOrder.getFest_or_price() + ",");
//			System.out.println(aFestOrder.getFest_or_start() + ",");
//			System.out.println(aFestOrder.getFest_or_send() + ",");
//			System.out.println(aFestOrder.getFest_or_end() + ",");
//			System.out.println(aFestOrder.getFest_or_disc() + ",");
//			System.out.println(aFestOrder.getCust_ID() + ",");
//		}
	}
	@Override
	public List<FestOrderVO> findByCustID(String cust_ID) {
		List<FestOrderVO> festOrderVOs = new ArrayList<FestOrderVO>();
		FestOrderVO festOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT_CUSTID);
			
			pstmt.setString(1, cust_ID);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
//				System.out.println("DAO" + "223");

				festOrderVO = new FestOrderVO();
				festOrderVO.setFest_or_ID(rs.getString(1));
				festOrderVO.setFest_or_status(rs.getString(2));
				festOrderVO.setFest_or_price(rs.getInt(3));
				festOrderVO.setFest_or_start(rs.getDate(4));
				festOrderVO.setFest_or_send(rs.getDate(5));
				festOrderVO.setFest_or_end(rs.getDate(6));
				festOrderVO.setFest_or_disc(rs.getString(7));
				festOrderVO.setCust_ID(rs.getString(8));
				festOrderVOs.add(festOrderVO);

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return festOrderVOs;
	}

}