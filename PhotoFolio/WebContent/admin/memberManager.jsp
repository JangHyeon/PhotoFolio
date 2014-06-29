
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.photofolio.DTO.Member"%>
<%@ page import="com.photofolio.DAO.AdminDao"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<style type="text/css">
 #memberManage{
 	text-align: center;
 }
</style>
<link type="text/css" rel="stylesheet" href="../css/coFmmon.css">
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="../include/header.jsp" />
		<div id="main" align="center">
			<div id="memberManage">
				<h2>회원관리</h2>
				<form action="" method="post">
					<table style="width: 900px; border: 1px solid; text-align: center;">
						<tr>
							<th>회원닉네임(아이디)</th>
							<th>정보보기</th>
							<th>수정(공사중...)</th>
							<th>삭제</th>
						</tr>
						<%
							List memberlist = (List) request.getAttribute("memberlist");
							for (int i = 0; i < memberlist.size(); i++) {
								Member member = (Member) memberlist.get(i);
						%>
						<tr>

							<td><%=member.getNickname()%>(<%=member.getId()%>)</td>
							<td><a
								href="../adminorder/memberView?id=<%=member.getId()%>">정보보기</td>
							<td><a href="">수정(공사중...)</td>
							<td><a
								href="../adminorder/memberDelete?id=<%=member.getId()%>">삭제</td>
						</tr>
						<%
							}
						%>
					</table>
					<a href="../adminorder/dashboard">목록</a>
				</form>
			</div>
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>