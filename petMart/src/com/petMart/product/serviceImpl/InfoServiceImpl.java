package com.petMart.product.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petMart.common.DAO;
import com.petMart.product.vo.QuestionVO;
import com.petMart.product.vo.ReviewVO;

public class InfoServiceImpl extends DAO{
	
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	public List<QuestionVO> selectQuestionList(String itemCode) {
		sql = "select * from product_question where item_code = ? order by question_id, depth";
		List<QuestionVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, itemCode);
			rs = psmt.executeQuery();
			while(rs.next()) {
				QuestionVO vo = new QuestionVO();
				vo.setQuestionId(rs.getInt("question_id"));
				vo.setItemCode(rs.getString("item_code"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setIsOpen(rs.getString("is_open"));
				vo.setDepth(rs.getInt("depth"));
				vo.setCanRead(rs.getString("canread"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
		
	public int reviewInsert(ReviewVO vo) {
		int r = 0;
		sql = "insert into product_review values(review_id_seq.nextval, ?, ?, sysdate, ?, ?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getItemCode());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			psmt.setInt(4, vo.getSatisfaction());
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;	
	}
	
	public int questionInsert(QuestionVO vo) {
		int r = 0;
		sql = "insert into product_question values(question_id_seq.nextval, ?, ?, ?, ?, sysdate, ?, 0, 'admin')";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getItemCode());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getContent());
			psmt.setString(4, vo.getWriter());
			psmt.setString(5, vo.getIsOpen());
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;	
	}
		
	
	public List<ReviewVO> selectReviewList(String itemCode) {
		sql = "select * from product_review where item_code = ? order by review_id";
		List<ReviewVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, itemCode);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ReviewVO vo = new ReviewVO();
				vo.setReviewId(rs.getInt("review_id"));
				vo.setItemCode(rs.getString("item_code"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setWriter(rs.getString("writer"));
				vo.setSatisfaction(rs.getInt("satisfaction"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}	

	private void close() {
		if(rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(psmt != null)
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
