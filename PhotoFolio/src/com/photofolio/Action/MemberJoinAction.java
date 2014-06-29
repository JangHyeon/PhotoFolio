package com.photofolio.Action;

import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.JoinDAO;
import com.photofolio.DTO.Member;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int result;
		JoinDAO joindao = new JoinDAO();
		Member memberdto = new Member();
		
		
		memberdto.setId(request.getParameter("id"));
		memberdto.setPwd(request.getParameter("pwd"));
		memberdto.setNickname(request.getParameter("nickname"));
		memberdto.setEmail(request.getParameter("email"));
		memberdto.setPhone(request.getParameter("phone"));
		memberdto.setAddress(request.getParameter("address"));
		memberdto.setProfileimg(request.getParameter("profileimg"));
		memberdto.setMemo(request.getParameter("setmemo"));
		result =  joindao.joinmember(memberdto);
	   
	    
	    System.out.println(result);
	    HttpSession session = request.getSession();
	    
		if(result>0){
			session.setAttribute("id",memberdto.getId());
			session.setAttribute("nickname",memberdto.getNickname());
			session.setAttribute("lvl",0);
		}
	    
	     String msg = "가입";
           String url = "../Sample.jsp";

        System.out.println(result);
		request.setAttribute("result", result);
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../redirect.jsp");
		return forward;
		
	}

}
