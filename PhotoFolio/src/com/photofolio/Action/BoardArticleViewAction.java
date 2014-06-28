//기존에 작업했던 서비스 계층
//글을 쓰는 서비스 
//Action 패키지 안에 구현

package com.photofolio.Action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.BoardDAO;
import com.photofolio.DTO.Article;
import com.photofolio.DTO.Image;
import com.photofolio.DTO.Member;
import com.photofolio.DTO.Reply;;


public class BoardArticleViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//유효성 체크
		if(request.getParameter("idx")==null || request.getParameter("idx").trim().equals("")){
			return null;
		}
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		
		//세션(로그인)정보 가져오기
		HttpSession session = request.getSession();
		String loginid = (String)session.getAttribute("id");
				
		//DAO
		BoardDAO dao = BoardDAO.getInstance();
		Article article = dao.articleView(idx);//게시물 정보
		List<Image> imageList = dao.imageView(idx);//이미지 정보
		Member member = dao.memberView(article.getId());//글쓴이 정보
		int replycount = dao.articleReplyCount(idx);//댓글 수
		if(!article.getId().equals(loginid)){//로그인한 사용자와 글쓴이가 다르면 조회수 업
			dao.updataCount(idx);
			article.setCount(article.getCount()+1);
		}
		List<Reply> replyList = dao.articleReplyList(idx);//댓글 리스트
		
		
		//Request에 담기
		request.setAttribute("article", article);
		request.setAttribute("imageList", imageList);
		request.setAttribute("member", member);
		request.setAttribute("replycount", replycount);
		request.setAttribute("replyList", replyList);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/board/view.jsp");
		return forward;
	}
}
