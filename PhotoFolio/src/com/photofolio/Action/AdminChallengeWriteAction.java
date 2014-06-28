package com.photofolio.Action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;



public class AdminChallengeWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String startdate	 = request.getParameter("startdate");
		String enddate	 = request.getParameter("enddate");
		String emblem = request.getParameter("emblem");
		
		//System.out.println("Action 넘어옴 " + subject + content + startdate + enddate + emblem );
		
		AdminDao dao = new AdminDao();
		dao.challengeWrite(subject, content, startdate, enddate, emblem);
		
		// forward 대한 처리 로직
	    ActionForward forward = new ActionForward();	
		forward.setRedirect(true);
		forward.setPath("../adminorder/challengeManeger");
		return forward;
		
	}
}
