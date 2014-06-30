package com.photofolio.Action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.DAO.LikeaddDAO;
import com.photofolio.DAO.LikedeleteDAO;



public class MemberLikedeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LikedeleteDAO likedelete = new LikedeleteDAO();
		String id = request.getParameter("id");
		String myid = request.getParameter("myid");
	   int result=  likedelete.likedelete(id, myid);
	   
	  
	   String msg = "관심삭제";
	   String url = "likeinfoprocess?id="+myid;
	   request.setAttribute("result", result);

	   request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../redirect.jsp");
		return forward;
	}

	
}
