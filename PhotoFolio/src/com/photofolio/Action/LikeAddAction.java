package com.photofolio.Action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.DAO.LikeAddDAO;
import com.photofolio.DAO.LikeinfoDAO;
import com.photofolio.DTO.Member;

public class LikeAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		LikeAddDAO likeadddao = new LikeAddDAO();	
		System.out.println("아이디  Action");
		System.out.println("list아이디  ACtion ");
		
		String id = request.getParameter("id");
		String listid = request.getParameter("listid");

		ArrayList<Member> list = new ArrayList<Member>();
		int rs = likeadddao.info(id, listid);
		System.out.println("LikeAddAction" + rs);
		if(rs>0){
			LikeinfoDAO dao = new LikeinfoDAO();
			list = dao.info(id);
		}
		
		ActionForward forward = new ActionForward();
		request.setAttribute("id", id);
		request.setAttribute("listid", listid);
		request.setAttribute("list",list);
		 request.setAttribute("check", "check");

		forward.setRedirect(false);
		forward.setPath("../member/likeinfo.jsp");
		
		return forward;
		
	}
	
	
}
