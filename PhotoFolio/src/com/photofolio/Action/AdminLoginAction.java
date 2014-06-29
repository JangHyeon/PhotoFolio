package com.photofolio.Action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photofolio.DAO.AdminDao;

public class AdminLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AdminDao dao = new AdminDao();
		// List boardlist = new ArrayList();

		int result = dao.selectadmin(request.getParameter("id"),
				request.getParameter("pwd"));
		ActionForward forward = new ActionForward();
		if (result == 1) {
			System.out.println("Admin 로그인성공");
			HttpSession session = request.getSession();
			session.setAttribute("result", result);

			forward.setRedirect(true);
			forward.setPath("../adminorder/dashboard");
		} else if (result == -1) {
			System.out.println("Admin 비밀번호가 다릅니다.");
			forward.setRedirect(true);
			forward.setPath("../admin/LoginAdmin.jsp");

		} else if (result == -2) {
			System.out.println("Admin 아이디가 없습니다.");
			forward.setRedirect(true);
			forward.setPath("../admin/LoginAdmin.jsp");

		} else {
			System.out.println("Admin 다시 시도하세요");
			forward.setRedirect(true);
			forward.setPath("../admin/LoginAdmin.jsp");

		}

		request.setAttribute("result", result);

		// forward 대한 처리 로직

		return forward;
	}
}
