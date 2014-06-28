package com.photofolio.Controllor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.Action.Action;
import com.photofolio.Action.ActionForward;
import com.photofolio.Action.AdminCallengeListAction;
import com.photofolio.Action.AdminCallengeModifyAction;
import com.photofolio.Action.AdminCallengeModifyOkAction;
import com.photofolio.Action.AdminCallengeViewAction;
import com.photofolio.Action.AdminChallengeDeleteAction;
import com.photofolio.Action.AdminChallengeWriteAction;
import com.photofolio.Action.AdminCreatorLevelDownAction;
import com.photofolio.Action.AdminEmblemDeleteAction;
import com.photofolio.Action.AdminEmblemListAction;
import com.photofolio.Action.AdminEmblemModifyAction;
import com.photofolio.Action.AdminEmblemModifyOkAction;
import com.photofolio.Action.AdminLoginAction;
import com.photofolio.Action.AdminMemberDeleteAction;
import com.photofolio.Action.AdminMemberLevelManagerAction;
import com.photofolio.Action.AdminMemberLevelUpAction;
import com.photofolio.Action.AdminMemberManagerAction;


@WebServlet("/adminorder/*")
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminFrontController() {
        super();
    }


    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	
    	
		String requestURI = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String command = requestURI.substring(ContextPath.length());
		
		System.out.println("RequestURI:" + requestURI);//RequestURI:/PhotoFolio/admin/adminorder/login
		System.out.println("ContextPath:" + ContextPath);//ContextPath:/PhotoFolio
		System.out.println("command:" + command); //command:/admin/adminorder/login
		
		ActionForward forward = null;
		Action action = null;
		
		//////////////////////////////////////////////////////////////
		
		if(command.equals("/adminorder/login")) //1. 관리자 로그인처리
		{
			System.out.println("adminlogin display");
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			System.out.println("id : " + id + " / " + "pwd : " + pwd);  //넘어오는지 확인
			
			action = new AdminLoginAction();
			try {
				forward = action.execute(request, response);
				System.out.println("SelectAfter");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//////////////////////////////////////////////////////////
			
		}else if(command.equals("/adminorder/memberManager")){ //2.1 회원관리 페이지
			System.out.println("MemberManager Admin Display");
			
			action = new AdminMemberManagerAction();
			try {
				System.out.println("memberManager Before");
				forward = action.execute(request, response);
				System.out.println("memberManager After");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/adminorder/memberDelete")){ //2.2 회원 삭제
			System.out.println("memberDelete Admin Display");
			action = new AdminMemberDeleteAction();
			try {
				System.out.println("memberDelete Before");
				forward = action.execute(request, response);
				System.out.println("memberDelete After");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		////////////////////////////////////////////////////////////////////////
			
		}else if(command.equals("/adminorder/memberLevelManager")){ //3.1 회원등급관리 페이지
			System.out.println("MemberLevelManager Admin Display");
			
			action = new AdminMemberLevelManagerAction();
			try {
				System.out.println("memberLevelManager Before");
				forward = action.execute(request, response);
				System.out.println("memberLevelManager After");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/adminorder/memberLevelUp")){//3.2 회원등급업 
			System.out.println("MemberLevelUp Admin Display");

			action = new AdminMemberLevelUpAction();
			try {
				forward = action.execute(request, response);
				System.out.println("memberLevelUp After");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/adminorder/creatorLevelDown")){ //3.3 크리에이터 강등
			System.out.println("creatorLevelDown Admin Display");
			
			action = new AdminCreatorLevelDownAction();
			try {
				forward = action.execute(request, response);
				System.out.println("creatorLevelDown After");
			} catch (Exception e) {
				e.printStackTrace();
			}
		////////////////////////////////////////////////////////////////
			
		}else if(command.equals("/adminorder/reportManager")){ //4. 신고관리
			System.out.println("reportManager Admin Display");
		}
			
	
		///////////////////////////////////////////////////////////////////
		
		else if(command.equals("/adminorder/challengeManeger")){ //5.1 챌린지 List
			System.out.println("ChallengeManager Admin Display");
			
			action = new AdminCallengeListAction();
			try {
				forward = action.execute(request, response);
				System.out.println("CahllengeWrite After"); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		else if(command.equals("/adminorder/ChallengeWrite")){ //5.2 챌린지 쓰기
			System.out.println("ChallengeWrite Admin Display");
		
			action = new AdminChallengeWriteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("CahllengeWrite After"); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/adminorder/challengeView")){//5.3 챌린지 보기
			System.out.println("ChallengeView Admin Display");
			action = new AdminCallengeViewAction();
			try {
				forward = action.execute(request, response);
				System.out.println("CahllengeView After"); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/adminorder/challengeModify")){//5.4 챌린지 수정View
			System.out.println("ChallengeModify Admin Display");
			action = new AdminCallengeModifyAction();
			try {
				forward = action.execute(request, response);
				System.out.println("ChallengeView After");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if(command.equals("/adminorder/ChallengeModifyOk")){
			System.out.println("ChallengeModifyOk Admin Display");

			action = new AdminCallengeModifyOkAction();
			try {
				forward = action.execute(request, response);
				System.out.println("ChallengeModifyOk After");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/adminorder/challengeDelete")){ //5.5 챌린지 삭제
			System.out.println("ChallengeDelete Admin Display");
			
			
			action = new AdminChallengeDeleteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("CahllengeDelete After"); 
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		////////////////////////////////////////////////////////////////////
		
		else if(command.equals("/adminorder/emblemManeger")){  //6.1 앰블럼 리스트 보기
			System.out.println("EmblemList Admin Display");
			action = new AdminEmblemListAction();
			try {
				forward = action.execute(request, response);
				System.out.println("emblemList After");
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		else if(command.equals("/adminorder/emblemModify")){ //6.2 앰블럼 수정view
			System.out.println("EmblemModify Admin Display");
			action = new AdminEmblemModifyAction();
			try {
				forward = action.execute(request, response);
				System.out.println("emblemModifyView After"); 
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		else if(command.equals("/adminorder/emblemModifyOk")){ //6.3 앰블럼 수정OK
			System.out.println("EmblemModifyOk Admin Display");
			action = new AdminEmblemModifyOkAction();
			try {
				forward = action.execute(request, response);
				System.out.println("emblemModifyOk After");
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		else if(command.equals("/adminorder/emblemDelete")){ //6.3 앰블럼 삭제
			System.out.println("EmblemDelete Admin Display");
			action = new AdminEmblemDeleteAction();
			try {
				forward = action.execute(request, response);
				System.out.println("emblemDelete After");
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		}
		
		
		
		
		if(forward != null){
			if(forward.isRedirect()){ 
				System.out.println("forward.isRedirect" + forward.isRedirect());
				response.sendRedirect(forward.getPath());
			}else{
				System.out.println("forward.getPath" + forward.getPath());
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
