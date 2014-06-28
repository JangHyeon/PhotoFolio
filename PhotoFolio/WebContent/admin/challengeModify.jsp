<%@page import="com.photofolio.DTO.Challenge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*" %>
<%
	List challengeModify = (List)request.getAttribute("challengeModify");
	System.out.println("넘어오겄나" +challengeModify);
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
			
				<%
				for(int i = 0 ; i < challengeModify.size() ; i++){
					Challenge challenge = (Challenge)challengeModify.get(i);
				
				%>
				<form action="../adminorder/ChallengeModifyOk" method="post">
				
				<div>
					<input type="hidden" name="emblem_no" value="<%=challenge.getEmblem_no()%>">
					<input type="text" size="80px"  name="subject" value="<%=challenge.getSubject() %>" ><br>
					<input type="text"  size="48px" name="startdate" value="<%=challenge.getStartdate() %>" >
					-<input type="text"  size="48px" name="enddate" value="<%=challenge.getEnddate() %>"><br>
					<textarea name="content" rows="" cols="90px"><%=challenge.getContent() %></textarea><br>
					<br>
				</div>


				<div>
					엠블럼 사진 등록하는 것 일단보류<br> 
					<input type="text" name="emblem" value="<%=challenge.getEmblem()%>">
				</div>
				
<%} %>				
				<div>
				<a href="../adminorder/challengeManeger">뒤로가기</a>
				
				<input type="submit" name="modify" value="수정">
				
				</div>
			</form>
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>