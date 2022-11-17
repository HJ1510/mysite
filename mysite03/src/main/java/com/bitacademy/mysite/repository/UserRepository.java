package com.bitacademy.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.exception.UserRepositoryException;
import com.bitacademy.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;

	public boolean update(UserVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			if ("".equals(vo.getPassword())) {
				String sql = "update user set name=?, gender=? where no=?"; // update할 값
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setLong(3, vo.getNo());

			} else {
				String sql = "update user set name=?, password=?, gender=? where no=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getGender());
				pstmt.setLong(4, vo.getNo());

			}

			int count = pstmt.executeUpdate(); // int count = pstmt.executeUpdate(sql); 에러!

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

	public UserVo findByNo(Long no) {
		return sqlSession.selectOne("user.findByNo", no);
	}

	public UserVo findByEmailAndPassword(String email, String password) throws UserRepositoryException{
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		return sqlSession.selectOne("user.findByEmailAndPassword", map);

	}

	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);		
		return count== 1;
	}

}
