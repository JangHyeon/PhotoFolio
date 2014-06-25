package com.photofolio.Action;

import javax.servlet.http.*;

public interface Action {
	//메서드
	public ActionForward execute(HttpServletRequest request,
			                     HttpServletResponse response) throws Exception;
	
}
