package com.photofolio.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.DAO.CheckDAO;


public class MembercheckidAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			String id = request.getParameter("id");
			
			CheckDAO checkdao = new CheckDAO();
			
			int data = checkdao.checkid(id);
			
			PrintWriter out = response.getWriter();
			System.out.println(data);
			out.print(data);
		   
		return null;
		
	}
}
