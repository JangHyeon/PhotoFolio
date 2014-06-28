package com.photofolio.Action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;



public class AdminEmblemDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int emblem_no = Integer.parseInt(request.getParameter("emblem_no"));
		
		AdminDao dao = new AdminDao();
		dao.emblemDelete(emblem_no);
		
		
		// forward 대한 처리 로직
	    ActionForward forward = new ActionForward();	
		forward.setRedirect(true);
		forward.setPath("../adminorder/emblemManeger");
		return forward;
		
	}
}
