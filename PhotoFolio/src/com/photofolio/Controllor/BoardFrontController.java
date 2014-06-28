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
import com.photofolio.Action.ArticleViewAction;
import com.photofolio.Action.ArticleWriteAction;
import com.photofolio.Action.ImageUpLoadAction;
import com.photofolio.Action.ImageWriteAction;
import com.photofolio.Action.ReplyWriteAction;
import com.photofolio.Action.ThumbnailCropperAction;
import com.photofolio.Action.ThumbnailUpLoadAction;

@WebServlet("/boardorder/*")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardFrontController() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//테스트용 세션 설정
		HttpSession session = request.getSession();
		
		session.setAttribute("id", "admin");
		session.setAttribute("nickname", "운영자");
		session.setAttribute("profileimg", "04545.jpg");
		
		
		
		
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

		// 일괄처리를 위해서
		ActionForward forward = null;
		Action action = null;

		if (commend.equals("/boardorder/thumbnailupload")) {//섬네일 업로드
			action = new ThumbnailUpLoadAction();
			try {
				action.execute(request, response);
				System.out.println("UpLoad : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/thumnbnailcropper")) {//섬네일 크롭
			action = new ThumbnailCropperAction();
			try {
				action.execute(request, response);
				System.out.println("Cropper : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/imageappend")) { //이미지 추가
			action = new ImageUpLoadAction();
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
			action = new ArticleWriteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("ArticleWrite : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (commend.equals("/boardorder/imagewrite")) {//이미지 정보 등록
			action = new ImageWriteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("ImageWrite : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/view")) {//게시물 뷰
			action = new ArticleViewAction();
			try {
				forward = action.execute(request, response);
				System.out.println("view : forward");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (commend.equals("/boardorder/replywrite")) {//댓글 등록
			action = new ReplyWriteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("replywrite : ajax");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		//forward
		if (forward != null) {
			if (forward.isRedirect()) {
				System.out.println("forward.isRedirect : " + forward.getPath());
				response.sendRedirect(forward.getPath());
			} else {
				System.out.println("forward.forward : " + forward.getPath());
				RequestDispatcher rd = request.getRequestDispatcher(forward
						.getPath());
				rd.forward(request, response);
			}
		}else{
			System.out.println("잘못된 페이지 요청 처리 해야됨");
		}
	}
}
