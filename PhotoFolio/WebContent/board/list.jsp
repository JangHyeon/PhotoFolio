<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 :: PhotoFolio</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<link href="<%=path%>/css/common.css" rel="stylesheet">
<link href="<%=path%>/css/list.css" rel="stylesheet">
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="/include/header.jsp" />

		<div id="main">
			<div class="topArea">
				<div class="titleArea">
					<h4>검색 결과&nbsp;<strong>${boardListCount}</strong>건</h4>
				</div>
				<div class="searchArea" id="inputbox">
					<%-- <input type="hidden" name="pageNum" value="${pageNum}"/> --%>
					<select name="category">
						<option value="">엠블럼</option>
						<fmt:requestEncoding value="UTF-8" />
						<c:forEach var="emblem" items="${emblemlist}">
							<option value="${emblem.emblem_no}" <c:if test="${param.emblem_no==emblem.emblem_no}">selected</c:if>>${emblem.emblem}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="lst_ty4">
				<ul>
					<c:forEach var="article" items="${articleList}">
					<li>
						<a href="<%=path%>/boardorder/view?idx=${article.idx}" class="effect_img">
							<p class="thmb">
								<img src="<%=path%>/storage/thumbnail/${article.thumbnail}" 
								width="288" height="216" class="img" alt="${article.subject}" onerror="this.src='../images/no288_216.jpg'">
								<span class="bdr"></span>
							</p>
						</a>
						<div class="info">
							<a href="<%=path%>/memberorder/likeinfoprocess?id=${article.id}" class="profile">
								<img src="<%=path%>/storage/profile/${article.profileimg}" 
							width="42" height="42" alt="profileImage" onerror="this.src='../images/no_profile_42.gif'">
							<span class="mask"></span></a>
							<p class="title"><a href="<%=path%>/boardorder/view?idx=${article.idx}">${article.subject}</a></p>
							<p class="author">by <a href="<%=path%>/memberorder/likeinfoprocess?id=${article.id}">${article.nickname}</a></p>
						</div>
						<p class="count">
							<span class="hit"><strong>조회</strong><em>${article.count}</em></span>
							<span class="like"><strong>좋아요</strong><em id="likeEl_24499">${article.like}</em></span>
							<span class="reply"><strong>댓글수</strong><em>${article.reply}</em></span>
						</p>
					
					</li>
					</c:forEach>
				</ul>
			</div>
			
			
			
			<div class="pageArea">
				<!-- 이전 링크 -->
				<c:if test="${beginpage>10}">
					<a href="<%=path%>/boardorder/list?pageNum=${pageNum-1}&pageSize=${pageSize}&emblem_no=${emblem_no}&searchKey=${searchKey}">이전</a>
				</c:if>
				<!-- 페이지 리스트   -->
				<c:forEach var="i" begin="${beginpage}" end="${endpage}" step="1">
					<c:if test="${i==pageNum}">
						<span class="current Page">${i}</span>
					</c:if>
					<c:if test="${i!=pageNum}">
						<span class="Page"><a href="<%=path%>/boardorder/list?pageNum=${i}&pageSize=${pageSize}&emblem_no=${emblem_no}&searchKey=${searchKey}">${i}</a></span>
					</c:if>
				</c:forEach>
				<!-- 다음링크 -->	
				<c:if test="${endpage<pagecount}">
					<a href="<%=path%>/boardorder/list?pageNum=${pageNum+1}&pageSize=${pageSize}&emblem_no=${emblem_no}&searchKey=${searchKey}">다음</a>
				</c:if> 
			</div>
			
		</div>
		<!-- Footer -->
		<jsp:include page="/include/footer.jsp" />
	</div>
</body>
</html>