package com.photofolio.Action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.photofolio.DAO.MemberAticleListDAO;
import com.photofolio.DAO.ModifyDAO;
import com.photofolio.DTO.Article;
import com.photofolio.DTO.Member;

public class MemberSelectList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		MemberAticleListDAO member = new MemberAticleListDAO();
		ArrayList<Article> memberArticle = new ArrayList<Article>();
		memberArticle = member.memberlist(id);
		
		for(int i =0;i<memberArticle.size();i++){
			System.out.println(memberArticle.get(i).getSubject());
			
		}
	
		ModifyDAO modifydao = new ModifyDAO();
		 ArrayList<Member> info = new ArrayList<Member>();// 상단 페이지 회원 정보
		 info = modifydao.modifymember(id);// 상단 페이지 회원 정보
		 
		 
		 
		 
		 request.setAttribute("info",info);
		request.setAttribute("memberArticle",memberArticle);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../member/likeinfoArticle.jsp");
		return forward;
		
	}

}
