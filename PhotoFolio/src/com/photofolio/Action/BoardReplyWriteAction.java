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

public class BoardReplyWriteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("idx-"+request.getParameter("idx"));
		System.out.println("id-"+request.getParameter("id"));
		System.out.println("nickname-"+request.getParameter("nickname"));
		System.out.println("content-"+request.getParameter("content"));
		System.out.println("profileimg-"+request.getParameter("profileimg"));
		
		//유효성 체크
		if((request.getParameter("idx")==null || request.getParameter("idx").trim().equals(""))&&
				(request.getParameter("id")==null || request.getParameter("id").trim().equals(""))&&
				(request.getParameter("nickname")==null || request.getParameter("nickname").trim().equals(""))&&
				(request.getParameter("content")==null || request.getParameter("content").trim().equals(""))&&
				(request.getParameter("profileimg")==null || request.getParameter("profileimg").trim().equals(""))){
			return null;
		}
		
		String path = request.getContextPath();
		int idx = Integer.parseInt(request.getParameter("idx"));
		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		String profileimg = request.getParameter("profileimg");
		Date today = new Date();
		System.out.println(today);

		
		
		Reply dto = new Reply();
		dto.setIdx(idx);
		dto.setId(id);
		dto.setContent(content);
		
		BoardDAO dao = BoardDAO.getInstance();
		int reply_idx = dao.replyWrite(dto);
				
		// 한글 처리를 위한 response 설정
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-chche");
		
		//업로드한 답글 리턴	
		PrintWriter outWriter = response.getWriter();
		String result ="";
		if(reply_idx>0){
			// 업로드 결과 전송
			result =  "<li id='comment_"+reply_idx+"'>";
			result += "<a href='"+path+"/memberorder/likeinfoprocess?id="+id +"' class='author'>";
			result += "<span class='thmb'>";
			result += "<img src=\""+path+"/storage/profile/"+profileimg+"\" width='32' height='32' " + 
						"alt='"+nickname+"' onerror=\"this.src='../images/no_profile_32.gif'\"><span class='mask'></span>";
			result += "</span>";
			result += "</a>";
			result += "<div class='info'>";
			result += "<a href=\""+path+"/memberorder/likeinfoprocess?id="+id +"\" class='name'>"+nickname+"("+dto.getId()+")</a>";
			result += "<span class='date'>"+ SupinanDateFormat.getInstance("yyyy.MM.dd HH.mm").format(today)+ "<em> new </em>" +"</span>";
			result += "<a href='#' class='del'>삭제</a>";
			result += "<span class='rt'>";
			result += "</span>";
			result += "</div>";
			result += "<p class='txt'>"+content+"</p>";
			result += "</li>";
		}else{
			result="0";
		}
		outWriter.print(result);
		outWriter.flush();
		
		return null;
	}
}