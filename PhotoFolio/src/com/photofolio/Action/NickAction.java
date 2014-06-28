package com.photofolio.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.DAO.IdchkDAO;

public class NickAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int data1;

		System.out.println(request.getParameter("nickname"));
		
		IdchkDAO idchkdao = new IdchkDAO();	
		data1 = idchkdao.NickDAO(request.getParameter("nickname"));		
			
		PrintWriter out = response.getWriter();
		
		out.print(data1);
		
		return null;
		
	}

}
