//기존에 작업했던 서비스 계층
//글을 쓰는 서비스 
//Action 패키지 안에 구현

package com.photofolio.Action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.DAO.BoardDAO;
import com.photofolio.DTO.Article;
import com.photofolio.Util.MyUtils;

;

public class BoardArticleListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 목록 초기값
		int emblem = 0, pageSize = 20, pageNum = 0;
		String searchKey = "";

		// page & 검색어 데이터 받을시
		if (request.getParameter("searchKey") != null) {
			if (!request.getParameter("searchKey").trim().equals("")) {
				searchKey = request.getParameter("searchKey");
			}
		}
		if (request.getParameter("emblem") != null) {
			if (!request.getParameter("emblem").trim().equals("")) {
				emblem = Integer.parseInt(request.getParameter("emblem"));
			}
		}
		if (request.getParameter("pageNum") != null) {
			if (!request.getParameter("pageNum").trim().equals("")) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
		}

		request.setAttribute("searchKey", searchKey);
		request.setAttribute("emblem", emblem);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);

		BoardDAO dao = BoardDAO.getInstance();

		// 검색된 총 게시물 건수 
		int boardListCount = dao.articleCount(searchKey, emblem);
		request.setAttribute("boardListCount", boardListCount);

		
		// 페이징 처리		
		int visiblePageNum = 10;
		int pagecount = 0;
		int beginPage = 0;
		int endPage = 0;
		if (boardListCount != 0) {// 게시물이 없는 경우
			pagecount = boardListCount / pageSize;// 115건 = 11page
			if (boardListCount % pageSize > 0) {// 115건 = 나머지 5 true
				pagecount++;// 11page++ = 12page
			}
			beginPage = (pageNum - 1) / visiblePageNum * visiblePageNum + 1;// 10단위
																			// 계산
			endPage = beginPage + (visiblePageNum - 1);
			if (endPage > pagecount) {
				endPage = pagecount;
			}
		}
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("beginpage", beginPage);
		request.setAttribute("endpage", endPage);

		// 엠블럼
		request.setAttribute("emblemlist", dao.emblemList());

	
		// list
		List<Article> articleList = dao.articleList(searchKey, emblem, pageNum, pageSize);

		// 7자 초과 닉네임
		// 영자30자 이상 제목 처리
		for (Article DTO : articleList) {
			String subject = DTO.getSubject();
			
			// 제목 영문 기준 30자 길이로 처리
			subject = MyUtils.getFixString(subject, 30);
			DTO.setSubject(subject);
			
			String nickname = DTO.getNickname();
			if (nickname != null && nickname.length() > 7) {
				nickname = nickname.substring(0, 7) + "..";
			}
			DTO.setNickname(nickname);
		}
		
		request.setAttribute("articleList", articleList);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/board/list.jsp");
		return forward;
	}
}
