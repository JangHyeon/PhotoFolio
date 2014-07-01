package com.photofolio.Action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.LikeaddDAO;
import com.photofolio.DAO.LikeinfoDAO;
import com.photofolio.DAO.ModifyDAO;
import com.photofolio.DTO.Member;

public class MemberLikeyouAction implements Action {
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		
			
		LikeinfoDAO likeinfodao = new LikeinfoDAO(); //info
		LikeinfoDAO  likeinfocheck = new LikeinfoDAO();//info2
		ModifyDAO modifydao = new ModifyDAO();
	
		String id = request.getParameter("id");
		 ArrayList<Member> list = new ArrayList<Member>();
		ArrayList<Member> checklist = new ArrayList<Member>();
				 
				 
		 ArrayList<Member> info = new ArrayList<Member>();// 상단 페이지 회원 정보
		 info = modifydao.modifymember(id);// 상단 페이지 회원 정보
	   list = likeinfodao.info2(id);
       checklist = likeinfocheck.info(id);
	   
		
	   for(int i =0; i<list.size();i++){//내가 추가한 친구가 맞는지 판단 하기위해 for문 사용 
		   for(int j=0; j<checklist.size();j++){
			   if( list.get(i).getId().equals(checklist.get(j).getId())){
				   
				
				   list.get(i).setCheckintreset("true");
				  

				   
			   }
			   
			   
		   }
		   
	   }
	   
	   
	   
	   
	   HttpSession session = request.getSession();
	   
	   String myid = (String)session.getAttribute("id");
	   
	      request.setAttribute("list",list);
	      request.setAttribute("checklist", checklist);
	      
	      
	      
		 request.setAttribute("info",info);
		 request.setAttribute("check","checkok");//내가 관심추가한 친구임을 판단 해주는 값
		ActionForward forward = new ActionForward();
	
	if(myid == null||myid.equals("")||myid.equals("null")){
			forward.setRedirect(true);
			forward.setPath("../member/login.jsp");
		}else{
			forward.setRedirect(false);
			forward.setPath("../member/likeinfo.jsp");
		}
		
		
		
		return forward;
		
	}
}
