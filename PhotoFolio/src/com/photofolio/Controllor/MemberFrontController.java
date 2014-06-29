package com.photofolio.Controllor;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.photofolio.Action.Action;
import com.photofolio.Action.ActionForward;
import com.photofolio.Action.MemberDeleteAction;
import com.photofolio.Action.MemberEditAction;
import com.photofolio.Action.MemberJoinAction;
import com.photofolio.Action.MemberLikeaddAction;
import com.photofolio.Action.MemberLikeyouAction;
import com.photofolio.Action.MemberLikemeAction;
import com.photofolio.Action.MemberLoginAction;
import com.photofolio.Action.MemberModifyAction;
import com.photofolio.Action.MembercheckidAction;
import com.photofolio.Action.profileimgUpLoadAction;
import com.photofolio.DAO.ModifyDAO;

/**
 * Servlet implementation class MemberFrontController
 */
@WebServlet("/memberorder/*")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String ContextPath = request.getContextPath();
		
		String command = RequestURI.substring(ContextPath.length());
		
		
		System.out.println("RequestURI : " + RequestURI);
		System.out.println("ContextPath : " + ContextPath);
		System.out.println("command : " + command);
		
		//일괄처리를 위해서
		ActionForward forward = null;
		Action action = null;
		
		
		if(command.equals("/memberorder/joinprocess"))
		{
			action = new MemberJoinAction();
			
			try {
					forward = action.execute(request, response);
				
			}catch(Exception e) {
					e.printStackTrace();
			}
		}else if(command.equals("/memberorder/logonprocess")){
	         
	  
	         action = new MemberLoginAction();
	         try {
	            forward = action.execute(request, response);
	         } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	          
	      }else if(command.equals("/memberorder/logoutprocess")){
	    	  
	    	  HttpSession session = request.getSession();
	    	  session.invalidate();
	    	  
	    	  forward = new ActionForward();
              forward.setRedirect(true);
              forward.setPath("../Sample.jsp");  
	      }else if(command.equals("/memberorder/modifyprocess")){
	    	  action = new MemberModifyAction();
		         try {
		            forward = action.execute(request, response);
		         } catch (Exception e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		         }
	    	  
	      }else if(command.equals("/memberorder/editprocess")){
	    	  action = new MemberEditAction();
		         try {
		            forward = action.execute(request, response);
		         } catch (Exception e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		         }
	      }else if(command.equals("/memberorder/deleteprocess")){
	            action = new MemberDeleteAction();
	            
	            try {
	               forward = action.execute(request, response);
	            
	            } catch (Exception e) {
	               e.printStackTrace();
	         }
	         }else if(command.equals("/memberorder/likeinfoprocess")){
	        	  action = new MemberLikemeAction();
		          
		            try {
		               forward = action.execute(request, response);
		            
		            } catch (Exception e) {
		               e.printStackTrace();
	         }
	         }else if(command.equals("/memberorder/likeinfo2process")){
	        	  action = new MemberLikeyouAction();
		            
		            try {
		               forward = action.execute(request, response);
		            
		            } catch (Exception e) {
		               e.printStackTrace();
	         }
	         }else if(command.equals("/memberorder/likeadd")){
	        	  action = new MemberLikeaddAction();
		            
		            try {
		               forward = action.execute(request, response);
		            
		            } catch (Exception e) {
		               e.printStackTrace();
	         }
	         }else if(command.equals("/memberorder/idcheckprocess")){
	        	String id= request.getParameter("id");
	        	  action = new MembercheckidAction();
		            
		            try {
		               forward = action.execute(request, response);
		            
		            } catch (Exception e) {
		               e.printStackTrace();
	        	 
	         }
	         }else if(command.equals("/memberorder/checknicknameprocess")){
	        		String id= request.getParameter("nickname");
		        	  action = new MembercheckidAction();
			            
			            try {
			               forward = action.execute(request, response);
			            
			            } catch (Exception e) {
			               e.printStackTrace();
		        	 
		         }
	         }else if(command.equals("/memberorder/profileupload")){
		        	  action = new profileimgUpLoadAction();
			          try {
			               forward = action.execute(request, response);
			            
			            } catch (Exception e) {
			               e.printStackTrace();
		         }
	         } 
		
		
		if(forward != null){
			if(forward.isRedirect()){ //view 단 바로....
				System.out.println("forward.isRedirect() : " + forward.isRedirect());
				response.sendRedirect(forward.getPath());
			}else{
				System.out.println("forward.getPath() : " + forward.getPath());
				RequestDispatcher dispatcher =
				request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	}
	


