//기존에 작업했던 서비스 계층
//글을 쓰는 서비스 
//Action 패키지 안에 구현

package com.photofolio.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.DAO.BoardDAO;
import com.photofolio.DTO.Image;

public class BoardImageWriteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("idx-"+request.getParameter("idx"));
		System.out.println("ref-"+request.getParameter("ref"));
		System.out.println("sub-"+request.getParameter("subject"));
		System.out.println("con-"+request.getParameter("content"));
		System.out.println("ori-"+request.getParameter("ori_name"));
		System.out.println("file-"+request.getParameter("file_name"));
		System.out.println("size-"+request.getParameter("file_size"));
		System.out.println("tag-"+request.getParameter("tag"));
				
		//유효성 체크
		if((request.getParameter("idx")==null || request.getParameter("idx").trim().equals(""))&&
			(request.getParameter("ref")==null || request.getParameter("ref").trim().equals(""))&&
			(request.getParameter("subject")==null || request.getParameter("subject").trim().equals(""))&&
			(request.getParameter("content")==null || request.getParameter("content").trim().equals(""))&&
			(request.getParameter("ori_name")==null || request.getParameter("ori_name").trim().equals(""))&&
			(request.getParameter("file_name")==null || request.getParameter("file_name").trim().equals(""))&&
			(request.getParameter("file_size")==null || request.getParameter("file_size").trim().equals(""))&&
			(request.getParameter("tag")==null || request.getParameter("tag").trim().equals(""))){
			return null;
		}
		
		Image dto = new Image();
		dto.setIdx(Integer.parseInt(request.getParameter("idx")));
		dto.setRef(Integer.parseInt(request.getParameter("ref")));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setOri_name(request.getParameter("ori_name"));
		dto.setFile_name(request.getParameter("file_name"));
		dto.setFile_size(Integer.parseInt(request.getParameter("file_size")));
		dto.setTag(request.getParameter("tag"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int result = dao.imageWrite(dto);
		
		//업로드한 게시물 idx값 리턴
		PrintWriter outWriter = response.getWriter();
		outWriter.print(result);
		outWriter.flush();
		
		return null;
	}
}
