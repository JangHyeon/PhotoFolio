package com.photofolio.Action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.LikeinfoDAO;
import com.photofolio.DAO.ModifyDAO;
import com.photofolio.DTO.Member;

public class LikemeAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		LikeinfoDAO likeinfodao = new LikeinfoDAO();

	
		String id = request.getParameter("id");
		 ArrayList<Member> list = new ArrayList<Member>();
	   list = likeinfodao.info(id);
		 request.setAttribute("list",list);
		 request.setAttribute("check", "checkno");

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../member/likeinfo.jsp");
		return forward;
		
	}
}