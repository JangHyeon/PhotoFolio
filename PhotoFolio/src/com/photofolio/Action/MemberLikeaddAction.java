package com.photofolio.Action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.DAO.LikeaddDAO;



public class MemberLikeaddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LikeaddDAO likeadd = new LikeaddDAO();
		String id = request.getParameter("id");
		String myid = request.getParameter("myid");
	   int result=  likeadd.likeadd(id, myid);
	   
	  
	   String msg = "관심추가"; 
	   String url = "likeinfo2process?id="+myid;
	   request.setAttribute("result", result);

	   request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../redirect.jsp");
		return forward;
	}

	
}
