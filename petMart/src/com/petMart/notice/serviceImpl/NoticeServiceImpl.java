package com.petMart.notice.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petMart.bulletin.vo.BulletinVO;
import com.petMart.common.DAO;
import com.petMart.notice.service.NoticeService;
import com.petMart.notice.vo.NoticeVO;


public class NoticeServiceImpl extends DAO implements NoticeService{
	
	PreparedStatement psmt;
	ResultSet rs;

	
	public List<NoticeVO> noticeListPaging(int page){
		String sql = "select b.*\r\n"
				+ "from(select rownum rn,a.*\r\n"
				+ "    from (select * from notice order by id) a\r\n"
				+ ") b\r\n"
				+ "where b.rn between ? and ?\r\n"
				+ "";

		List<NoticeVO> list = new ArrayList<>();
		int firstCnt = 0, lastCnt = 0;
		firstCnt = (page - 1) * 10 + 1; // 1
		lastCnt = (page * 10); // 10
		// 3페이지면, (3-1) * 10 + 1; = 21 ~ 30
		// 4페이지면, (4-1) * 10 + 1; = 31 ~ 40
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, firstCnt);
			psmt.setInt(2, lastCnt);
			rs = psmt.executeQuery();
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setId(rs.getInt("id"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setTitle(rs.getString("title"));
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
	public List<NoticeVO> noticeSelectList() {
		String sql = "select * from notice order by 1";
		List<NoticeVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("contents"));
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

	@Override
	public NoticeVO noticeSelect(NoticeVO vo) {
		String sql = "select * from notice where id = ?";
		NoticeVO nvo = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				nvo = new NoticeVO();
				hitCount(vo.getId());
				nvo.setId(rs.getInt("id"));
				nvo.setTitle(rs.getString("title"));
				nvo.setContent(rs.getString("content"));
				nvo.setRegDate(rs.getDate("reg_date"));
				nvo.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nvo;
	}

	@Override
	public int insertNotice(NoticeVO vo) {
		// String sql = "insert into notice values((select max(id)+1 from notice), ?, ?, sysdate, 0)";
		String sql = "insert into notice values(notice_seq.nextval, ?, ?, sysdate, 0)";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	@Override
	public int updateNotice(NoticeVO vo) {
		String sql = "update notice set title = ?, content = ? where id = ?";
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
	public int deleteNotice(NoticeVO vo) {
		String sql = "delete from notice where id = ?";
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
	
	private void hitCount(int id) {
		String sql = "update notice set hit = hit + 1 where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<NoticeVO> homePageNoticeList(){
		String sql = "select * from (select * from notice order by reg_date desc) where rownum <= 7";
		List<NoticeVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("contents"));
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
