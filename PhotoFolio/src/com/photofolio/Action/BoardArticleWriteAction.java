//기존에 작업했던 서비스 계층
//글을 쓰는 서비스 
//Action 패키지 안에 구현

package com.photofolio.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.BoardDAO;
import com.photofolio.DTO.Article;


public class BoardArticleWriteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		//유효성 체크
		if((request.getParameter("subject")==null || request.getParameter("subject").trim().equals(""))&&
			(request.getParameter("content")==null || request.getParameter("content").trim().equals(""))&&
			(request.getParameter("secret")==null || request.getParameter("secret").trim().equals(""))&&
			(request.getParameter("thumbnail")==null || request.getParameter("thumbnail").trim().equals(""))){
			return null;
		}
		
		//세션(로그인정보) 가져오기
		HttpSession session = request.getSession();
		
		Article dto = new Article();
		dto.setId((String)session.getAttribute("id"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setSecret(request.getParameter("secret").equals("true")?1:0);
		dto.setThumbnail(request.getParameter("thumbnail"));

		
		BoardDAO dao = BoardDAO.getInstance();
		
		int idx = dao.boardWrite(dto);
		
		//업로드한 게시물 idx값 리턴
		PrintWriter outWriter = response.getWriter();
		outWriter.print(idx);
		outWriter.flush();
		
		return null;
	}
}
