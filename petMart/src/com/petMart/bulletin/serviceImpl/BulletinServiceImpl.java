package com.petMart.bulletin.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petMart.bulletin.service.BulletinService;
import com.petMart.bulletin.vo.BulletinVO;
import com.petMart.common.DAO;
import com.petMart.notice.vo.NoticeVO;


public class BulletinServiceImpl extends DAO implements BulletinService{
	
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	private void hitCount(int id) {
		String sql = "update bulletin set hit = hit + 1 where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<BulletinVO> bulletinSelectListPaging(int page){
		sql = "select b.* from (select rownum rn, a.* from (select * from bulletin order by id) a ) b where b.rn between ? and ?";
		List<BulletinVO> list = new ArrayList<>();
		int firstCnt = 0, lastCnt = 0;
		firstCnt = (page - 1) * 10 + 1;
		lastCnt = page * 10;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, firstCnt);
			psmt.setInt(2, lastCnt);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BulletinVO vo = new BulletinVO();
				vo.setId(rs.getInt("id"));
				vo.setHit(rs.getInt("hit"));
				vo.setContent(rs.getString("content"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegDate(rs.getDate("reg_date"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;

	}
	
	@Override
	public List<BulletinVO> bulletinSelectList() {
		sql = "select * from bulletin order by 1";
		List<BulletinVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BulletinVO vo = new BulletinVO();
				vo.setId(rs.getInt("id"));
				vo.setHit(rs.getInt("hit"));
				vo.setContent(rs.getString("content"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegDate(rs.getDate("reg_date"));				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public BulletinVO bulletinselect(BulletinVO vo) {
		sql = "select * from bulletin where id = ?";
		BulletinVO bvo = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				bvo = new BulletinVO();
				hitCount(vo.getId());
				bvo.setId(rs.getInt("id"));
				bvo.setHit(rs.getInt("hit"));
				bvo.setContent(rs.getString("content"));
				bvo.setTitle(rs.getString("title"));
				bvo.setWriter(rs.getString("writer"));
				bvo.setRegDate(rs.getDate("reg_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bvo;
	}

	@Override
	public int insertBulletin(BulletinVO vo) {
		sql = "insert into bulletin values(bulletin_seq.nextval, ?, ?, ?, sysdate, 0)";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public int updateBulletin(BulletinVO vo) {
		sql = "update bulletin set title = ?, content = ? where id = ?";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, vo.getId());
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	@Override
	public int deleteBulletin(BulletinVO vo) {
		sql = "delete from bulletin where id = ?";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
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

	public List<BulletinVO> homePageBulletinList(){
		String sql = "select * from (select * from bulletin order by id desc) where rownum <= 7";
		List<BulletinVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BulletinVO vo = new BulletinVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setHit(rs.getInt("hit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
}
