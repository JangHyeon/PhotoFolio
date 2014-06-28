package com.photofolio.Action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;
import com.photofolio.DTO.Challenge;



public class AdminCallengeModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int emblem_no = Integer.parseInt(request.getParameter("emblem_no")) ;
		//System.out.println("action 제목" + subject);
		
		AdminDao dao = new AdminDao();
		List<Challenge> challengeModify = new ArrayList<Challenge>();
		
		challengeModify = dao.challengeView(emblem_no);
		request.setAttribute("challengeModify", challengeModify); 
		
		
		// forward 대한 처리 로직
		
	    ActionForward forward = new ActionForward();	
		forward.setRedirect(false);
		forward.setPath("../admin/challengeModify.jsp");
		return forward;
		
	}
}