package com.petMart.product.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petMart.common.DAO;
import com.petMart.notice.vo.NoticeVO;
import com.petMart.product.service.ProductService;
import com.petMart.product.vo.CartVO;
import com.petMart.product.vo.ProductVO;

public class ProductServiceImpl extends DAO implements ProductService {

	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	
	@Override
	public List<ProductVO> productSelectList() {
		sql = "select * from product order by 1";
		List<ProductVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setItemCode(rs.getString("item_code"));
				vo.setItemDesc(rs.getString("item_desc"));
				vo.setItemImage(rs.getString("item_image"));
				vo.setItemName(rs.getString("item_name"));
				vo.setLikeIt(rs.getInt("like_it"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale(rs.getString("sale"));
				vo.setSalePrice(rs.getInt("sale_price"));
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
	public ProductVO productSelect(ProductVO vo) {
		return null;
	}

	@Override
	public int insertProduct(ProductVO vo) {
		return 0;
	}

	@Override
	public int updateProduct(ProductVO vo) {
		return 0;
	}

	@Override
	public int deleteProduct(ProductVO vo) {
		return 0;
	}

	// 홈페이지 좋아요 순 top5 상품
	public List<ProductVO> homePageProductList(){
		String sql = "select * from (select * from product order by like_it desc) where rownum <= 5";
		List<ProductVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setItemImage(rs.getString("item_image"));
				vo.setItemDesc(rs.getString("item_desc"));
				vo.setLikeIt(rs.getInt("like_it"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale(rs.getString("sale"));
				vo.setSalePrice(rs.getInt("sale_price"));
				vo.setItemCode(rs.getString("item_code"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	
	// cart 정보 추가
	
	public void addCart(String id, String item, int qty) {
		sql = "insert into cart values(?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, item);
			psmt.setInt(3, qty);
			int r = psmt.executeUpdate();
			System.out.println(r+"건 장바구니에 입력");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
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
	
	public List<CartVO> cartList(String id) {
		sql = "select * from\r\n"
				+ "(select user_id, item_code, sum(item_qty) qty from cart\r\n"
				+ "group by user_id, item_code) cart, product p\r\n"
				+ "where cart.item_code = p.item_code\r\n"
				+ "and cart.user_id = ?";
		List<CartVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				CartVO vo = new CartVO();
				vo.setUserId(rs.getString("user_id"));
				vo.setItemCode(rs.getString("item_code"));
				vo.setItemQty(rs.getInt("qty"));
				vo.setItemDesc(rs.getString("item_desc"));
				vo.setItemImage(rs.getString("item_image"));
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
