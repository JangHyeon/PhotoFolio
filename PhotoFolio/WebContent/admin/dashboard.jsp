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
<link type="text/css" rel="stylesheet" href="../css/common.css">
<style type="text/css">
#main {
	width: 980px;
	height: 900px;
	/* background-color: black; */
}

#sidebar {
	width: 100px;
	height: 700px;
	/* background-color :yellow; */
	float: left;
}

#sidebar_menu li {
	border-bottom: 1px solid;
}

#maintable {
	width: 800px;
	height: 700px;
	/* background-color :green; */
	float: left;
	padding: 20px;
	text-align: center;
}

#table1 {
	width: 350px;
	height: 300px;
	background-color: white;
	float: left;
	padding: 10px;
	margin: 10px;
}

#table2 {
	width: 350px;
	height: 300px;
	background-color: white;
	float: left;
	padding: 10px;
	margin: 10px;
}

#table3 {
	width: 350px;
	height: 300px;
	background-color: white;
	float: left;
	padding: 10px;
	margin: 10px;
}

#table4 {
	width: 350px;
	height: 300px;
	background-color: white;
	float: left;
	padding: 10px;
	margin: 10px;
}
</style>
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="../include/header.jsp" />

		<div id="main" align="center">


			<h1>대시보드</h1>
			<div id="sidebar">
				<ul id=sidebar_menu>
					<li><a href="../adminorder/dashboard">대시보드</a></li>
					<li><a href="../adminorder/memberManager">회원관리</a></li>
					<li><a href="../adminorder/memberLevelManager">회원등급관리</a></li>
					<li><a href="../admin/reportManager.jsp">신고관리</a></li>
					<li><a href="../adminorder/challengeManeger">챌린지관리</a></li>
					<li><a href="../adminorder/emblemManeger">앰블럼관리</a></li>
					<li><a href="../adminorder/adminLogout">로그아웃</a></li>
				</ul>
			</div>
			<div id="maintable">

				<!-- ★회원관리 summary -->
				<div id="table1">
					<div id="memberManage">
						회원관리 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
							href="../adminorder/memberManager">더보기</a>
						<form action="../adminorder/" method="post">
							<table border="1">
								<tr>
									<th>회원닉네임(아이디)</th>
									<th>정보보기</th>
									<th>수정</th>
									<th>삭제</th>
								</tr>
								<%
									List memberlist = (List) request.getAttribute("memberlist");
									for (int i = 0; i < 8; i++) {
										Member member = (Member) memberlist.get(i);
								%>
								<tr>

									<td><%=member.getNickname()%>(<%=member.getId()%>)</td>
									<td><a href="">정보보기</td>
									<td><a href="">수정</td>
									<td><a
										href="../adminorder/memberDelete?id=<%=member.getId()%>">삭제</td>
								</tr>
								<%
									}
								%>
							</table>

						</form>
					</div>

				</div>





				<!-- ★회원등급관리 summary -->
				<div id="table2">
					회원등급관리 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="../adminorder/memberLevelManager">더보기</a>
					<table>
						<tr>
							<th>회원아이디</th>
							<th>회원등급</th>
							<th>좋아요</th>
							<th>등급조정</th>
						</tr>

						<%
							List memberLevelList = (List) request
									.getAttribute("memberLevelList");

							for (int i = 0; i < 8; i++) {
								Member member = (Member) memberLevelList.get(i);
						%>

						<tr>
							<td><%=member.getNickname()%>(<%=member.getId()%>)</td>
							<td><%=member.getLevel()%></td>
							<td>
								<%-- <%=member.getLike() %> --%>
							</td>
							<td><a
								href="../adminorder/memberLevelUp?id=<%=member.getId()%>">등급업</a></td>

						</tr>
						<%
							}
						%>
					</table>


				</div>



				<div id="table3">페이지공사중입니다..</div>
				<div id="table4">페이지공사중입니다...</div>
			</div>






		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>