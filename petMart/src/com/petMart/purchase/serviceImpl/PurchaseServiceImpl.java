package com.petMart.purchase.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petMart.common.DAO;
import com.petMart.notice.vo.NoticeVO;
import com.petMart.product.vo.CartVO;
import com.petMart.product.vo.ProductVO;
import com.petMart.purchase.service.PurchaseService;
import com.petMart.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl extends DAO implements PurchaseService {

	PreparedStatement psmt;
	ResultSet rs;
	String sql;



	@Override
	public int insertPurchase(PurchaseVO vo) {
				String sql = "insert into purchase values(?, ?, ?, ?, ?,?,?)";
				int r = 0;
				try {
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, vo.getUserId());
					psmt.setString(2, vo.getItemCode());
					psmt.setString(3, vo.getItemName());
					psmt.setInt(4, vo.getPrice());
					psmt.setString(5, vo.getSale());
					psmt.setInt(6, vo.getSalePrice());
					psmt.setInt(7, vo.getItemQty());
					r = psmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close();
				}
				return r;
	}
	
	@Override
	public List<PurchaseVO> purchaseSelectList() {
		sql = "select * from product order by 1";
		List<PurchaseVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				PurchaseVO vo = new PurchaseVO();
				vo.setUserId(rs.getString("id"));
				vo.setItemCode(rs.getString("item_code"));
				vo.setItemName(rs.getString("item_name"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale(rs.getString("sale"));
				vo.setSalePrice(rs.getInt("sale_price"));
				vo.setItemQty(rs.getInt("item_count"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	

	

	// 회원 cart 정보 추가
	public void addCart(String id, String item, int qty) {
		sql = "insert into cart values(?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, item);
			psmt.setInt(3, qty);
			int r = psmt.executeUpdate();
			System.out.println(r+"건 회원 장바구니에 입력");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public int deleteCart(PurchaseVO vo) {
		sql= "delete from cart where item_code=? and user_id = ?";
		int r = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getItemCode());
			psmt.setString(2, vo.getUserId());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}
	
	public int getCountCart(String id) {
		sql = "select count(*) from cart where user_id = ?";
		int rCnt = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				rCnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rCnt;
	}
	
	
	
	public List<PurchaseVO> purchaseList(String id) {
		sql = "select * from purchase where id=?";
		List<PurchaseVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				PurchaseVO vo = new PurchaseVO();
				vo.setUserId(rs.getString("id"));
				vo.setItemCode(rs.getString("item_code"));
				vo.setItemQty(rs.getInt("itme_qty"));
				vo.setItemName(rs.getString("item_name"));
				vo.setPrice(rs.getInt("price"));
				vo.setSalePrice(rs.getInt("sale_price"));
				vo.setSale(rs.getString("sale"));
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
	public PurchaseVO purchaseSelect(PurchaseVO vo) {
		String sql = "SELECT  m.id, p.item_code, p.item_name, p.price, p.sale, p.sale_price, c.item_qty\r\n"
				+ "FROM member m JOIN cart c\r\n"
				+ "ON (m.id = c.user_id)\r\n"
				+ "JOIN product p\r\n"
				+ "ON (p.item_code = c.item_code)\r\n"
				+ "where m.id= ? and p.item_code=?";
		
		PurchaseVO vo1 = new PurchaseVO();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(1, vo.getItemCode());
			rs = psmt.executeQuery();
			if(rs.next()) {
				
				vo.setUserId(rs.getString("id"));
				vo.setItemCode(rs.getString("item_code"));
				vo.setItemName(rs.getString("item_name"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale(rs.getString("sale"));
				vo.setSalePrice(rs.getInt("sale_price"));
				vo.setItemQty(rs.getInt("item_qty"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo1;
	}








	@Override
	public int updatePurchase(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int deletePurchase(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return 0;
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
