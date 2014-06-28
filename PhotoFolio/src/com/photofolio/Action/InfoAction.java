package com.photofolio.Action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.InfoDAO;
import com.photofolio.DAO.ModifyDAO;
import com.photofolio.DTO.Member;

public class InfoAction implements Action {
	
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("인포액션!");

		InfoDAO infodao = new InfoDAO();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		List<Member> list = new ArrayList<Member>();
		
		list = infodao.info(id);	
		
		request.setAttribute("list", list);
		
		for(int i = 0; i < list.size(); i++){
			System.out.println("액!션!"+list.get(i).getNickname());
		}
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../member/info.jsp");
	
		return forward;
	}

}

