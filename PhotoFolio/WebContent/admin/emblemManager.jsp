<%@page import="com.photofolio.DTO.Member"%>
<%@page import="com.photofolio.DTO.Emblem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	List<Emblem> emblemList = (List) request.getAttribute("emblemList");
	List<Member> memberlist = (List) request.getAttribute("memberlist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="../css/common.css">
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="../include/header.jsp" />

		<div id="main" align="center">
			<h2>앰블럼관리</h2>

			<div>
				<table style="width: 900px; border: 1px solid; text-align: center;">
					<tr>
						<th>앰블럼명</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>

					<%
						for (int i = 0; i < emblemList.size(); i++) {
							Emblem emblem = emblemList.get(i);
					%>

					<tr>
						<td><%=emblem.getEmblem()%></td>
						<td><a
							href="../adminorder/emblemModify?emblem_no=<%=emblem.getEmblem_no()%>">수정</a></td>
						<td><a
							href="../adminorder/emblemDelete?emblem_no=<%=emblem.getEmblem_no()%>">삭제</a></td>


					</tr>
					<%
						}
					%>
					<table style="width: 900px; border: 1px solid; text-align: center;">
			</div>

			<!-- 앰블럼명 별 정렬하기 -->

			<div>
				앰블럼명 List : <select id="emblemSelect">

					<%
						for (int i = 0; i < emblemList.size(); i++) {
							Emblem emblem2 = emblemList.get(i);
					%>
					<option><%=emblem2.getEmblem()%></option>

					<%
						}
					%>
				</select>

				<table style="width: 900px; border: 1px solid; text-align: center;">
					<tr>
						<th>회원아이디</th>
						<th>좋아요</th>
						<th>앰블럼부여</th>
					</tr>


					<%
						for (int i = 0; i < memberlist.size(); i++) {
							Member member = memberlist.get(i);
					%>
					<tr>
						<td><%=member.getId()%></td>
						<td><%=member.getLike()%></td>
						<td><a href="">앰블럼부여(공사중...)</a></td>

					</tr>
					<%
						}
					%>

					<table style="width: 900px; border: 1px solid; text-align: center;">
			</div>


			<a href="../adminorder/dashboard">목록</a>
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>