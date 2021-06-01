package com.petMart.member.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.petMart.bulletin.vo.BulletinVO;
import com.petMart.common.DAO;
import com.petMart.member.service.MemberService;
import com.petMart.member.vo.MemberVO;


public class MemberServiceImpl extends DAO implements MemberService{

	PreparedStatement psmt;
	ResultSet rs;
	
	@Override
	public List<MemberVO> selectMemberList() {
		return null;
	}

	@Override
	public MemberVO selectMember() {
		return null;
	}
	// 로그인 ID, PWD 체크 함수
	public MemberVO loginCheck(MemberVO vo) {
		String sql = "select * from member where id = ? and passwd = ?";
		MemberVO rvo = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPwd());
			rs = psmt.executeQuery();
			if(rs.next()) {
				rvo = new MemberVO();
				rvo.setId(rs.getString("id"));
				rvo.setPwd(rs.getString("passwd"));
				rvo.setName(rs.getString("name"));
				rvo.setAddr(rs.getString("address"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rvo;
	}
	
	
	// ID 중복 체크 함수
	public boolean idCheck(String id) {
		boolean exist = false;
		String sql = "select * from member where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				exist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return exist;
	}

	@Override
	public int insertMember(MemberVO vo) {
		int r = 0;
		String sql = "insert into member(id, name, passwd, address, tel) values(?,?,?,?,?)";
		try {
			System.out.println(vo.getId() + "Imple");
			System.out.println(vo.getName() + vo.getPwd() + vo.getAddr());
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getPwd());
			psmt.setString(4, vo.getAddr());
			psmt.setString(5, vo.getTel());
			r = psmt.executeUpdate();
			System.out.println(r+"건 입력됨");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	@Override
	public int updateMember(MemberVO vo) {
		return 0;
	}

	@Override
	public int deleteMember(MemberVO vo) {
		String sql = "delete from member where id=? and passwd=?";
		int r =0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPwd());
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

}
