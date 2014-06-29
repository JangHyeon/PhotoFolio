package com.photofolio.Controllor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.Action.Action;
import com.photofolio.Action.ActionForward;
import com.photofolio.Action.BoardArticleDeleteAction;
import com.photofolio.Action.BoardArticleLikeAction;
import com.photofolio.Action.BoardArticleListAction;
import com.photofolio.Action.BoardArticleRandomViewAction;
import com.photofolio.Action.BoardArticleReportAction;
import com.photofolio.Action.BoardArticleViewAction;
import com.photofolio.Action.BoardArticleWriteAction;
import com.photofolio.Action.BoardImageUpLoadAction;
import com.photofolio.Action.BoardImageWriteAction;
import com.photofolio.Action.BoardReplyDeleteAction;
import com.photofolio.Action.BoardReplyReportAction;
import com.photofolio.Action.BoardReplyWriteAction;
import com.photofolio.Action.BoardThumbnailCropperAction;
import com.photofolio.Action.BoardThumbnailUpLoadAction;

@WebServlet("/boardorder/*")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardFrontController() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//테스트용 세션 설정
		//session.setAttribute("id", "admin");
		//session.setAttribute("nickname", "운영자");
		//session.setAttribute("profileimg", "04545.jpg");
		
		
		// Command 패턴 기반의 parameter를 통해 제어
		// 1. 요청 받기
		String RequestURI = request.getRequestURI();
		String ContextPath = request.getContextPath();

		String commend = RequestURI.substring(ContextPath.length());

		//System.out.println("RequestURI : " + RequestURI);
		//System.out.println("ContextPath : " + ContextPath);
		//System.out.println("command : " + commend);

		// RequestURI : /Web_JSP17_ServletBoard/boardList.do
		// ContextPath : /Web_JSP17_ServletBoard
		// command : /boardList.do

		
		// 2. 요청 처리

		// 일괄처리를 위한 action, actionforward
		ActionForward forward = null;
		Action action = null;

		
		HttpSession session = request.getSession();
		System.out.println("---------------로그인 ID : "+(String)session.getAttribute("id"));
		
				
		//////////////////로그인 없이 사용가능한 커맨드
		if (commend.equals("/boardorder/view")) {//게시물 뷰
			action = new BoardArticleViewAction();
			try {
				forward = action.execute(request, response);
				System.out.println("view : forward");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(commend.equals("/boardorder/randomarticle")){//랜덤 게시물
			action = new BoardArticleRandomViewAction();
			try {
				forward = action.execute(request, response);
				System.out.println("randomarticle : forward");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}else if(commend.equals("/boardorder/list")){//게시물 리스트
			action = new BoardArticleListAction();
			try {
				forward = action.execute(request, response);
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/board/list.jsp");
				System.out.println("list : forward");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}else if(session.getAttribute("id")==null){/////////////////////////////////로그인 체크가 필요한 커맨드
        	request.setAttribute("msg", "로그인이 필요한 서비스 입니다.");
			request.setAttribute("url", ContextPath+"/member/login.jsp");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/board/redirect.jsp");
		}else if (commend.equals("/boardorder/thumbnailupload")) {//섬네일 업로드
			action = new BoardThumbnailUpLoadAction();
			try {
				action.execute(request, response);
				System.out.println("UpLoad : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/thumnbnailcropper")) {//섬네일 크롭
			action = new BoardThumbnailCropperAction();
			try {
				action.execute(request, response);
				System.out.println("Cropper : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/imageappend")) { //이미지 추가
			action = new BoardImageUpLoadAction();
			try {
				action.execute(request, response);
				System.out.println("Append : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/upload")) {//업로드페이지(Redirect)			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/board/upload.jsp");
		}else if (commend.equals("/boardorder/articlewrite")) {//새글 등록
			action = new BoardArticleWriteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("ArticleWrite : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (commend.equals("/boardorder/imagewrite")) {//이미지 정보 등록
			action = new BoardImageWriteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("ImageWrite : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/replywrite")) {//댓글 등록
			action = new BoardReplyWriteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("replywrite : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/replydelete")) {//댓글 삭제
			action = new BoardReplyDeleteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("replydelete : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/replyreport")) {//댓글 신고
			action = new BoardReplyReportAction();
			try {
				forward = action.execute(request, response);
				System.out.println("replyreport : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/articlereport")) {//게시물 신고
			action = new BoardArticleReportAction();
			try {
				forward = action.execute(request, response);
				System.out.println("articlereport : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/articledelete")) {//게시물 삭제
			action = new BoardArticleDeleteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("articledelete : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/articlelike")) {//게시물 좋아요
			action = new BoardArticleLikeAction();
			try {
				forward = action.execute(request, response);
				System.out.println("articledelete : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		

		
		
		//forward
		if (forward != null) {
			if (forward.isRedirect()) {
				System.out.println("forward.isRedirect : " + forward.getPath());
				response.sendRedirect(forward.getPath());
			}else{
				System.out.println("forward.forward : " + forward.getPath());
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}
	}
}
