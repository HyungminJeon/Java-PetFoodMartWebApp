package com.petMart.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petMart.bulletin.web.BulletinDelete;
import com.petMart.bulletin.web.BulletinForm;
import com.petMart.bulletin.web.BulletinInsert;
import com.petMart.bulletin.web.BulletinList;
import com.petMart.bulletin.web.BulletinListPaging;
import com.petMart.bulletin.web.BulletinSelect;
import com.petMart.bulletin.web.BulletinUpdate;
import com.petMart.member.web.MemberCancel;
import com.petMart.member.web.MemberCancelForm;
import com.petMart.member.web.MemberJoin;
import com.petMart.member.web.MemberJoinForm;
import com.petMart.member.web.MemberLogOut;
import com.petMart.member.web.MemberLogin;
import com.petMart.member.web.MemberLoginFail;
import com.petMart.member.web.MemberLoginForm;
import com.petMart.member.web.SendSMS;
import com.petMart.notice.web.Notice;
import com.petMart.notice.web.NoticeDelete;
import com.petMart.notice.web.NoticeInsert;
import com.petMart.notice.web.NoticeInsertForm;
import com.petMart.notice.web.NoticeList;
import com.petMart.notice.web.NoticeListPaging;
import com.petMart.notice.web.NoticeUpdate;
import com.petMart.product.web.AddCart;
import com.petMart.product.web.AfterDeleteGetCartCount;
import com.petMart.product.web.CartList;
import com.petMart.product.web.GetCartCount;
import com.petMart.product.web.ProductList;
import com.petMart.product.web.ProductListPaging;
import com.petMart.product.web.SearchList;
import com.petMart.product.web.ShowQuestion;
import com.petMart.product.web.ShowReview;
import com.petMart.purchase.web.AfterPurchaseCartCount;
import com.petMart.purchase.web.PurchaseList;
import com.petMart.product.web.AjaxDeleteCartList;


public class FrontController extends HttpServlet{
	
	private HashMap<String, DbCommand> map = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		// ex: map.put("/~.do", new ~~());
		// 시작 시 메인 페이지 호출, 메뉴 바 클릭 시 메인 페이지 호출
		map.put("/homePage.do", new HomePage());
		
		// 로그인, 회원 가입
		map.put("/memberJoinForm.do",new MemberJoinForm());
		map.put("/memberJoin.do", new MemberJoin());
		map.put("/memberLoginForm.do", new MemberLoginForm());
		map.put("/memberLogin.do", new MemberLogin());
		map.put("/memberLoginOut.do", new MemberLogOut());
		map.put("/memberLoginFail.do", new MemberLoginFail());
		map.put("/memberCancelForm.do", new MemberCancelForm());
		map.put("/memberCancel.do", new MemberCancel());
		
		// 장바구니
		map.put("/productList.do", new ProductList());
		map.put("/addCart.do", new AddCart());
		map.put("/getCartCount.do", new GetCartCount());
		map.put("/afterDeleteCartCount.do", new AfterDeleteGetCartCount());
		map.put("/cartList.do", new CartList());
		map.put("/productListPaging.do", new ProductListPaging());
		
		// 상품 상세보기
		map.put("/showReview.do", new ShowReview());
		map.put("/showQuestion.do", new ShowQuestion());
		
		// 메뉴 바
		map.put("/searchList.do", new SearchList());
		map.put("/getCartCount.do", new GetCartCount());
		
		// 자유 게시판
		map.put("/bulletinList.do", new BulletinList());
		map.put("/bulletinForm.do", new BulletinForm());
		map.put("/bulletinInsert.do", new BulletinInsert());
		map.put("/bulletinSelect.do", new BulletinSelect());
		map.put("/bulletinUpdate.do", new BulletinUpdate());
		map.put("/bulletinDelete.do", new BulletinDelete());
		map.put("/bulletinListPaging.do", new BulletinListPaging());
		
		// 공지사항
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeListPaging.do", new NoticeListPaging());
		map.put("/notice.do", new Notice());
		map.put("/noticeUpdate.do", new NoticeUpdate());
		map.put("/noticeInsert.do", new NoticeInsert());
		map.put("/noticeInsertForm.do", new NoticeInsertForm());
		map.put("/noticeForm.do", new NoticeInsertForm());
		map.put("/noticeDelete.do", new NoticeDelete());
		
		//결제
		map.put("/purchaseList.do", new PurchaseList());
		//map.put("/afterPurchaseCartCount.do", new AfterPurchaseCartCount());
		
		
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI();
		String cpath = req.getContextPath();
		String path = uri.substring(cpath.length());
		System.out.println(path);
		
		DbCommand dbCommand = map.get(path);
		String viewPage = dbCommand.execute(req, resp);
		
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
		
		
	}
}
