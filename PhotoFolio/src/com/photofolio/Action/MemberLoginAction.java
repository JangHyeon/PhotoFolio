package com.photofolio.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.JoinDAO;
import com.photofolio.DAO.LoginDAO;
import com.photofolio.DTO.Member;


public class MemberLoginAction implements Action {
   
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 int result =0;
		System.out.println("현재 session 정보 : " );
			LoginDAO logindao = new LoginDAO();
			Member memberdto = new Member();
		 	HttpSession session =  request.getSession();

			memberdto.setId(request.getParameter("id"));
			memberdto.setPwd(request.getParameter("pwd"));
			result = logindao.loginmember(memberdto);
		    
			if(result>0){
				session.setAttribute("id", memberdto.getId());
				session.setAttribute("nickname", memberdto.getNickname());
				session.setAttribute("lvl", memberdto.getLevel());
				session.setAttribute("profileimg", memberdto.getProfileimg());
			}
			
		    String msg = "로그인";
	        String url = request.getContextPath()+"/member/login.jsp";

	       
			request.setAttribute("result", result);
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("../redirect.jsp");
			return forward;
			
	}

}
