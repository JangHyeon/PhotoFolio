package com.photofolio.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.EditDAO;
import com.photofolio.DTO.Member;

public class MemberEditAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int result;
		EditDAO editdao = new EditDAO();
		Member memberdto = new Member();
		
		HttpSession session = request.getSession();
		memberdto.setId((String)session.getAttribute("id"));
		memberdto.setLevel((Integer)session.getAttribute("lvl"));
		memberdto.setPwd(request.getParameter("pwd"));
		memberdto.setNickname(request.getParameter("nickname"));
		memberdto.setEmail(request.getParameter("email"));
		memberdto.setPhone(request.getParameter("phone"));
		memberdto.setAddress(request.getParameter("address"));
		memberdto.setProfileimg(request.getParameter("profileimg"));
		memberdto.setMemo(request.getParameter("memo"));
		memberdto.setHomepage(request.getParameter("homepage"));
		memberdto.setHistory(request.getParameter("history"));
		memberdto.setCmemo(request.getParameter("cmemo"));
		result =  editdao.editMember(memberdto);
		
		session.setAttribute("nickname", memberdto.getNickname());
	    
	
	    
	     String msg = "수정";
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
