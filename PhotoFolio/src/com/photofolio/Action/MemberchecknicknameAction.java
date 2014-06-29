package com.photofolio.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.DAO.CheckDAO;


public class MemberchecknicknameAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			String nickname = request.getParameter("nickname");
			
			CheckDAO checkdao = new CheckDAO();
			
			int data = checkdao.checknickname(nickname);
			
			PrintWriter out = response.getWriter();
			System.out.println(data);
			out.print(data);
		   
		return null;
		
	}
}
