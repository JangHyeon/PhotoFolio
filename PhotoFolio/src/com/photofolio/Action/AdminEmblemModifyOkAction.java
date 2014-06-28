package com.photofolio.Action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;
import com.photofolio.DTO.Challenge;



public class AdminEmblemModifyOkAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int emblem_no = Integer.parseInt(request.getParameter("emblem_no"));
		String emblem = request.getParameter("emblem");
		String eimg= request.getParameter("eimg");
		
		AdminDao dao = new AdminDao();
		
		dao.emblemModifyOk(emblem_no, emblem, eimg);
		
		
		
		// forward 대한 처리 로직
		
	    ActionForward forward = new ActionForward();	
		forward.setRedirect(true);
		forward.setPath("../adminorder/emblemManeger");
		return forward;
		
	}
}
