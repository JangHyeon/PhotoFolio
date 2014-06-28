package com.photofolio.Action;

import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.DAO.IdchkDAO;
import com.photofolio.DAO.JoinDAO;

public class IdchkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int data;

		System.out.println(request.getParameter("id"));
		
		IdchkDAO idchkdao = new IdchkDAO();	
		data = idchkdao.IdchkDAO(request.getParameter("id"));		
			
		PrintWriter out = response.getWriter();
		
		out.print(data);
		
		return null;
	}

}
