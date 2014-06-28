<%@page import="com.photofolio.DTO.Challenge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<%
	List challengeList = (List)request.getAttribute("challengeList");
	//System.out.println(challengeList); 확인완료

%>
<link type="text/css" rel="stylesheet" href="../css/common.css">
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="../include/header.jsp"/>

		<div id="main">
				<h2>챌린지관리</h2>
				
				<table border="1">
					<tr>
						<th>챌린지명</th>
						<th>보기</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
<%
	for(int i =0; i <challengeList.size(); i++){
		Challenge challenge =(Challenge)challengeList.get(i);
	
%>
					<tr>
						<td><%=challenge.getSubject() %></td>
						<td><a href="../adminorder/challengeView?emblem_no=<%=challenge.getEmblem_no()%>">보기</a></td>
						<td><a href="../adminorder/challengeModify?emblem_no=<%=challenge.getEmblem_no()%>">수정</a></td>
						<td><a href="../adminorder/challengeDelete?emblem_no=<%=challenge.getEmblem_no()%>">삭제</a></td>
					</tr>
<% }%>
				</table>
				<a href="../admin/dashboard.jsp">목록</a>
				<a href="../admin/challengeWrite.jsp">등록</a>
				
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp"/>

	</div>
</body>
</html>
