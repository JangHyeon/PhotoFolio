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

public class BoardReplyDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("reply_idx-"+request.getParameter("reply_idx"));
		
		//유효성 체크
		if((request.getParameter("reply_idx")==null || request.getParameter("reply_idx").trim().equals(""))){
			return null;
		}
		
		int reply_idx = Integer.parseInt(request.getParameter("reply_idx"));

		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.replyDelete(reply_idx);
		
		//삭제 결과 리턴
		PrintWriter outWriter = response.getWriter();
		
		outWriter.print(result);
		outWriter.flush();
		
		return null;
	}
}