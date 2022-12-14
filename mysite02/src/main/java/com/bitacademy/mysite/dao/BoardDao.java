package com.bitacademy.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitacademy.mysite.vo.BoardVo;

public class BoardDao {

//	public Boolean replyInsert(BoardVo vo) {
//		boolean result = false;
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = "insert into board values(null, ? , ? , 0 , now(), ? , ? , ?, ?)";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContents());
//			pstmt.setInt(3, vo.getGroupNo());
//			pstmt.setInt(4, vo.getOrderNo());
//			pstmt.setInt(5, vo.getDepth());
//			pstmt.setLong(6, vo.getUserNo());
//			//
//
//			int count = pstmt.executeUpdate();
//
//			result = count == 1;
//
//		} catch (SQLException e) {
//			System.out.println("Error:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return result;
//	}
	
	public void hitCountUp(BoardVo vo) {
		int result = -1;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set hit=hit+1 where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public boolean update(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "update board set title=?, contents=? where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

//			System.out.println(":"+vo.getTitle());
//			System.out.println(":"+vo.getContents());
//			System.out.println(":"+vo.getNo());

			int count = pstmt.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {

			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public Boolean insert(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board values(null, ? , ? , 0 , now(), 1 , 1 , 0, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());
			//

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public BoardVo findByNo(Long no) {
		BoardVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select title , contents, user_no from board where no= ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String title = rs.getString(1);
				String contents = rs.getString(2);
				Long userNo = rs.getLong(3);

				result = new BoardVo();
				result.setTitle(title);
				result.setContents(contents);
				result.setUserNo(userNo);
			}

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) { // ?????? ????????? ?????? ????????????
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public Boolean deleteByNo(Long no) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from board where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select a.no, a.title, a.hit, date_format(a.reg_date, '%Y/%m/%d %H:%i:%s' ), a.depth , a.user_no, b.name from board a, user b where a.user_no =b.no order by no desc, group_no desc, order_no asc";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Long no = rs.getLong(1);
				String title = rs.getString(2);

				int hit = rs.getInt(3);
				String regDate = rs.getString(4);
				int depth = rs.getInt(5);
				Long userNo = rs.getLong(6);
				String name = rs.getString(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setName(name);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (rs != null) { // ?????? ????????? ?????? ????????????
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found:" + e);
		}
		return conn;
	}


}
