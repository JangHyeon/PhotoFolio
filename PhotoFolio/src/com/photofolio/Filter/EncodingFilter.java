package com.photofolio.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
@WebFilter(urlPatterns={"/*"})
public class EncodingFilter implements Filter{

	private String Encoding ="UTF-8";
	@Override
	public void init(FilterConfig fconfig) throws ServletException {
	
	}
	@Override
	public void doFilter(ServletRequest req	, ServletResponse res,
			FilterChain Chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		if(req.getCharacterEncoding() == null){
			req.setCharacterEncoding(this.Encoding);
			//System.out.println("after :" + req.getCharacterEncoding());
		}
		Chain.doFilter(req, res);
		//System.out.println("response");
	}

	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.Encoding = null;
	}

	
}
