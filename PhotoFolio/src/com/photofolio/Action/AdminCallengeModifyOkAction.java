package com.photofolio.Action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;
import com.photofolio.DTO.Challenge;



public class AdminCallengeModifyOkAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int emblem_no = Integer.parseInt(request.getParameter("emblem_no"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String emblem = request.getParameter("emblem");
		System.out.println("action : " + subject + content + startdate + emblem);
		
		AdminDao dao = new AdminDao();
		
		dao.challengeModifyOk(subject, content, startdate, enddate, emblem_no, emblem);
		
		
		
		// forward 대한 처리 로직
		
	    ActionForward forward = new ActionForward();	
		forward.setRedirect(true);
		forward.setPath("../adminorder/challengeManeger");
		return forward;
		
	}
}
