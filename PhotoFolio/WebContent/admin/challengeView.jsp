<%@page import="com.photofolio.DTO.Challenge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*" %>
<%
	List challengeView = (List)request.getAttribute("challengeView");
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

		<div id="main">
			<h2>챌린지관리(보기)</h2>
			<form action="../adminorder/ChallengeWrite" method="post">
				<%
				for(int i = 0 ; i < challengeView.size() ; i++){
					Challenge challenge = (Challenge)challengeView.get(i);
				
				%>
				<div>
					<input type="text" size="80px"  name="subject" value="<%=challenge.getSubject() %>" readonly="readonly"><br>
					<input type="text"  size="48px" name="startdate" value="<%=challenge.getStartdate() %>" readonly="readonly">
					-<input type="text"  size="48px" name="enddate" value="<%=challenge.getEnddate() %>"readonly="readonly"><br>
					<textarea name="content" rows="" cols="90px" readonly="readonly"><%=challenge.getContent() %></textarea><br>
					<br>
				</div>


				<div>
					엠블럼 사진 등록하는 것 일단보류<br> 
					<input type="text" name="emblem" value="<%=challenge.getEmblem()%>" readonly="readonly">
				</div>
				
<%} %>				
				<div>
				<a href="../adminorder/challengeManeger">뒤로가기</a>
				<!-- <input type="submit" name="write" value="등록"> -->
				
				</div>
			</form>
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>