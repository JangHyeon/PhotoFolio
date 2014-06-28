package com.photofolio.Action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;



public class AdminMemberLevelManagerAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AdminDao dao = new AdminDao();
		
		List memberLevelList = new ArrayList();
		List creatorLevelList = new ArrayList();
		
		memberLevelList = dao.memberLevelManagerMain();
		
		AdminDao dao2 = new AdminDao();
		creatorLevelList = dao2.creatorLevelManagerMain();
		
		request.setAttribute("memberLevelList", memberLevelList); 
		request.setAttribute("creatorLevelList", creatorLevelList); 
		
		
		// forward 대한 처리 로직
		
	    ActionForward forward = new ActionForward();	
		forward.setRedirect(false);
		forward.setPath("../admin/memberLevelManager.jsp");
		return forward;
		
	}
}
