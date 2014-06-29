package com.photofolio.Action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.LikeinfoDAO;
import com.photofolio.DAO.ModifyDAO;
import com.photofolio.DTO.Member;

public class MemberLikeyouAction implements Action {
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		LikeinfoDAO likeinfodao = new LikeinfoDAO();
		ModifyDAO modifydao = new ModifyDAO();
	
		String id = request.getParameter("id");
		 ArrayList<Member> list = new ArrayList<Member>();
		 ArrayList<Member> info = new ArrayList<Member>();
		 ArrayList<Member> infocheck = new ArrayList<Member>();
		 
				 
		 
		 info = modifydao.modifymember(id);
	   list = likeinfodao.info2(id);

	   info = modifydao.modifymember(id);
		 request.setAttribute("list",list);
		 request.setAttribute("info",info);
		 request.setAttribute("check", "checkok");
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../member/likeinfo.jsp");
		
		
		return forward;
		
	}
}