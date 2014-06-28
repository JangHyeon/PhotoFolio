package com.photofolio.Action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;



public class AdminEmblemListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		AdminDao embledao = new AdminDao();
		List emblemList = new ArrayList();
		
		//앰블럼 불러오기
		emblemList = embledao.emblemManagerMain();
		request.setAttribute("emblemList", emblemList); 
		
		
		AdminDao memberdao = new AdminDao();
		List memberlist = new ArrayList();
		
		//회원 불러오기
		memberlist = memberdao.memberManagerMain();
		request.setAttribute("memberlist", memberlist); 
		
		
		
		// forward 대한 처리 로직
		
	    ActionForward forward = new ActionForward();	
		forward.setRedirect(false);
		forward.setPath("../admin/emblemManager.jsp");
		return forward;
		
	}
}
