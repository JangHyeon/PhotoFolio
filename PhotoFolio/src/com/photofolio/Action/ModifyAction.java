package com.photofolio.Action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.photofolio.DAO.ModifyDAO;
import com.photofolio.DTO.Member;

public class ModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		ModifyDAO modifydao = new ModifyDAO();
		Member dto = new Member();
		
	 	HttpSession session =  request.getSession();
		String id = (String)session.getAttribute("id");
		int lvl = (Integer)session.getAttribute("lvl");
		 
		dto = modifydao.modifymember(id, lvl);
	   
	    request.setAttribute("dto", dto);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../member/modify.jsp");
		return forward;
		
	}

}
