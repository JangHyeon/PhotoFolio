package com.photofolio.Action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;



public class AdminCreatorLevelDownAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		//System.out.println("Action id : " + id);
		
		AdminDao dao = new AdminDao();
		dao.creatorLevelDown(id);
		
		// forward 대한 처리 로직
	    ActionForward forward = new ActionForward();	
		forward.setRedirect(true);
		forward.setPath("../adminorder/memberLevelManager");
		return forward;
		
	}
}
