package com.bitacademy.mysite.repository;

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
	
	public void hitCountUp(BoardVo vo) {
		sqlSession.update("board.hitCountUp", vo);
	}

	public boolean update(BoardVo vo) {
		int count = sqlSession.update("board.update", vo);
//		System.out.println("3:"+vo);
		return count == 1;
	}

	public Boolean deleteByNo(Long no) {
		int count = sqlSession.delete("board.deleteByNo", no);
		return count == 1;
	}

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
	


}
