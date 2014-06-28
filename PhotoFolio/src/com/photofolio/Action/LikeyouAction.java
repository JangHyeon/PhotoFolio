package com.photofolio.Action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.LikeinfoDAO;
import com.photofolio.DTO.Member;

public class LikeyouAction implements Action {
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		LikeinfoDAO likeinfodao = new LikeinfoDAO();
		
			
	     String id = request.getParameter("id");
		 ArrayList<Member> list = new ArrayList<Member>();
	     list = likeinfodao.info2(id);
		 request.setAttribute("list",list);
		 request.setAttribute("check", "checkok");
		 
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../member/likeinfo.jsp");
		
		return forward;
		
	}
}
