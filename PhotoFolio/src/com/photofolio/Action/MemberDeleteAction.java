package com.photofolio.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.DeleteDAO;

public class MemberDeleteAction implements Action {

	   @Override
	   public ActionForward execute(HttpServletRequest request,
	         HttpServletResponse response) throws Exception {
	            int result;
	      HttpSession session = request.getSession();
	      DeleteDAO deletedao = new DeleteDAO();
	      String id = (String)session.getAttribute("id");
	      int lvl = (Integer)session.getAttribute("lvl");
	     result =	deletedao.Delete(id,lvl);
	     	session.invalidate();
	     
	     String msg = "탈퇴";
         String url = "../member/login.jsp";

    
		request.setAttribute("result", result);
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../redirect.jsp");
		return forward;
	   }

	}
