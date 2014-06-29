<%@page import="com.photofolio.DTO.Emblem"%>
<%@page import="com.photofolio.DTO.Challenge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	List emblemModify = (List) request.getAttribute("emblemModify");
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
			<h2>앰블럼관리(수정)</h2>


			<%
				for (int i = 0; i < emblemModify.size(); i++) {
					Emblem emblem = (Emblem) emblemModify.get(i);
			%>
			<form
				action="../adminorder/emblemModifyOk?emblem_no=<%=emblem.getEmblem_no()%>"
				method="post">
				<div>
					엠블럼 사진 등록하는 것 일단보류<br> <input type="text" name="emblem"
						value="<%=emblem.getEmblem()%>">
				</div>
				<%
					}
				%>
				<div>
					<a href="../adminorder/emblemManeger">뒤로가기</a> <input type="submit"
						name="modify" value="수정">

				</div>
			</form>
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>