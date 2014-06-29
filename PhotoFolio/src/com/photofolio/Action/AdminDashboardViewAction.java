package com.photofolio.Action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;
import com.photofolio.DTO.Challenge;



public class AdminDashboardViewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//1. 회원관리
		AdminDao dao = new AdminDao();
		List memberlist = new ArrayList();
		memberlist = dao.memberManagerMain();
		request.setAttribute("memberlist", memberlist); 
		
		
		//2. 회원등급관리
		AdminDao memberLevelDao = new AdminDao();
		List memberLevelList = new ArrayList();

		memberLevelList = memberLevelDao.memberLevelManagerMain();
		request.setAttribute("memberLevelList", memberLevelList);  
		
		// forward 대한 처리 로직
		
	    ActionForward forward = new ActionForward();	
		forward.setRedirect(false);
		forward.setPath("../admin/dashboard.jsp");
		return forward;
		
	}
}
