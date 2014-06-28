package com.photofolio.Action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;



public class AdminEmblemModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AdminDao dao = new AdminDao();
		List emblemModify = new ArrayList();
		int emblem_no =  Integer.parseInt(request.getParameter("emblem_no"));
		
		emblemModify = dao.emblemModifyView(emblem_no);
		request.setAttribute("emblemModify", emblemModify); 
		
		
		// forward 대한 처리 로직
		
	    ActionForward forward = new ActionForward();	
		forward.setRedirect(false);
		forward.setPath("../admin/emblemModify.jsp");
		return forward;
		
	}
}
