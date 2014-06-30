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
		
		
		
			
		LikeinfoDAO likeinfodao = new LikeinfoDAO();
		LikeinfoDAO  likeinfocheck = new LikeinfoDAO();
		ModifyDAO modifydao = new ModifyDAO();
	
		String id = request.getParameter("id");
		 ArrayList<Member> list = new ArrayList<Member>();
		 ArrayList<Member> info = new ArrayList<Member>();
		ArrayList<Member> checklist = new ArrayList<Member>();
				 
				 
		info = modifydao.modifymember(id); 
		 info = modifydao.modifymember(id);
	   list = likeinfodao.info2(id);
       checklist = likeinfocheck.info(id);
	   
		
	   for(int i =0; i<list.size();i++){
		   for(int j=0; j<checklist.size();j++){
			   if( list.get(i).getId().equals(checklist.get(j).getId())){
				   
				   
				   list.get(i).setCheckintreset("true");
				   
                      System.out.println(list.get(i).getId()+"/"+list.get(i).getCheckintreset());

				   
			   }
			   
			   
		   }
		   
	   }
	   
	   
	   
	   
	   HttpSession session = request.getSession();
	   
	   String myid = (String)session.getAttribute("id");
	   
	      request.setAttribute("list",list);
	      request.setAttribute("checklist", checklist);
	      
	      
	      
		 request.setAttribute("info",info);
		 request.setAttribute("check","checkok");
		ActionForward forward = new ActionForward();
	
	if(myid == null||myid.equals("")||myid.equals("null")){
			forward.setRedirect(true);
			forward.setPath("../Sample.jsp");
		}else{
			forward.setRedirect(false);
			forward.setPath("../member/likeinfo.jsp");
		}
		
		
		
		return forward;
		
	}
}
