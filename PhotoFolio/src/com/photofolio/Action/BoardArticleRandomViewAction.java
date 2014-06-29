//기존에 작업했던 서비스 계층
//글을 쓰는 서비스 
//Action 패키지 안에 구현

package com.photofolio.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.DAO.BoardDAO;


public class BoardArticleRandomViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		//DAO
		BoardDAO dao = BoardDAO.getInstance();
		
		//랜덤게시물 추출
		int idx = dao.randomArticle();	
		
		//Request에 담기
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath(request.getContextPath()+"/boardorder/view?idx="+idx);
		return forward;
	}
}