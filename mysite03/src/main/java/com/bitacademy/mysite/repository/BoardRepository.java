package com.bitacademy.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.BoardVo;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;
	
////	public Boolean replyInsert(BoardVo vo) {
////		boolean result = false;
////
////		Connection conn = null;
////		PreparedStatement pstmt = null;
////
////		try {
////			conn = getConnection();
////
////			String sql = "insert into board values(null, ? , ? , 0 , now(), ? , ? , ?, ?)";
////			pstmt = conn.prepareStatement(sql);
////
////			pstmt.setString(1, vo.getTitle());
////			pstmt.setString(2, vo.getContents());
////			pstmt.setInt(3, vo.getGroupNo());
////			pstmt.setInt(4, vo.getOrderNo());
////			pstmt.setInt(5, vo.getDepth());
////			pstmt.setLong(6, vo.getUserNo());
////			//
////
////			int count = pstmt.executeUpdate();
////
////			result = count == 1;
////
////		} catch (SQLException e) {
////			System.out.println("Error:" + e);
////		} finally {
////			try {
////				if (pstmt != null) {
////					pstmt.close();
////				}
////
////				if (conn != null) {
////					conn.close();
////				}
////			} catch (SQLException e) {
////				e.printStackTrace();
////			}
////		}
////
////		return result;
////	}
//	
////	public void hitCountUp(BoardVo vo) {
////		int result = -1;
////
////		Connection conn = null;
////		PreparedStatement pstmt = null;
////
////		try {
////			conn = getConnection();
////
////			String sql = "update board set hit=hit+1 where no=?";
////			pstmt = conn.prepareStatement(sql);
////			pstmt.setLong(1, vo.getNo());
////			
////			result = pstmt.executeUpdate();
////
////		} catch (SQLException e) {
////
////			System.out.println("Error:" + e);
////		} finally {
////			try {
////				if (pstmt != null) {
////					pstmt.close();
////				}
////
////				if (conn != null) {
////					conn.close();
////				}
////			} catch (SQLException e) {
////				e.printStackTrace();
////			}
////		}
////
////	}
//
//	public boolean update(BoardVo vo) {
//		boolean result = false;
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = "update board set title=?, contents=? where no=?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContents());
//			pstmt.setLong(3, vo.getNo());
//
////			System.out.println(":"+vo.getTitle());
////			System.out.println(":"+vo.getContents());
////			System.out.println(":"+vo.getNo());
//
//			int count = pstmt.executeUpdate();
//
//			result = count == 1;
//		} catch (SQLException e) {
//
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
//
//
//
//
//
//	public Boolean deleteByNo(Long no) {
//		boolean result = false;
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = "delete from board where no = ?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setLong(1, no);
//
//			int count = pstmt.executeUpdate();
//
//			result = count == 1;
//
//		} catch (SQLException e) {
//			System.out.println("Error : " + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//
//	}

	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}
	
	public Boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;
	}
	
	public BoardVo findByNo(Long no) {
//		System.out.println("3:"+no);
		return sqlSession.selectOne("board.findByNo", no);
	}
	


//	private Connection getConnection() throws SQLException {
//		Connection conn = null;
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//		} catch (ClassNotFoundException e) {
//			System.out.println("Class Not Found:" + e);
//		}
//		return conn;
//	}


}
