//기존에 작업했던 서비스 계층
//글을 쓰는 서비스 
//Action 패키지 안에 구현

package com.photofolio.Action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.DAO.BoardDAO;
import com.photofolio.DTO.Reply;
import com.supinan.util.text.SupinanDateFormat;

public class BoardArticleLikeAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("idx-"+request.getParameter("idx"));
		
		//유효성 체크
		if((request.getParameter("idx")==null || request.getParameter("idx").trim().equals(""))){
			return null;
		}
		
		int idx = Integer.parseInt(request.getParameter("idx"));

		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.articleLike(idx);
		
		//좋아요 결과 리턴
		PrintWriter outWriter = response.getWriter();
		
		outWriter.print(result);
		outWriter.flush();
		
		return null;
	}
}