package com.photofolio.Controllor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/*")
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminFrontController() {
        super();
    }


    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}
}
