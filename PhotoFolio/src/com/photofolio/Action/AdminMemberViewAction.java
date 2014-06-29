package com.photofolio.Action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;



public class AdminMemberViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		
		AdminDao dao = new AdminDao();
		List memberList = new ArrayList();
		
		memberList = dao.memberView(id);
		request.setAttribute("memberList", memberList); 
		
		
		// forward 대한 처리 로직
		
	    ActionForward forward = new ActionForward();	
		forward.setRedirect(false);
		forward.setPath("../admin/memberView.jsp");
		return forward;
		
	}
}
