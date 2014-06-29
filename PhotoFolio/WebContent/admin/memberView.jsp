<%@page import="com.photofolio.DTO.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	List<Member> memberList = (List) request.getAttribute("memberList");
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


			<form action="../memberorder/joinprocess" id="joinform" method="post">
				<%
					for (int i = 0; i < memberList.size(); i++) {
						Member member = memberList.get(i);
				%>


				아이디: <input type="text" id="id" name="id"
					value="<%=member.getId()%>"><br> 비밀번호 : <input
					type="password" name="pwd" value="<%=member.getPwd()%>"><br>
				닉네임 : <input type="text" name="nickname"
					value="<%=member.getNickname()%>"><br> 휴대폰번호 : <input
					type="text" name="phone" value="<%=member.getPhone()%>"><br>
				이메일 : <input type="text" name="email" value="<%=member.getEmail()%>"><br>

				주소 : <input type="text" name="address"
					value="<%=member.getAddress()%>"><br> 프로필이미지 <br>
				<img
					src="<%=request.getContextPath()%>/storage/profile/<%=member.getProfileimg()%>"><br>
				프로필 <br>
				<textarea rows="10" cols="30" name="profile"
					style="resize: none; overflow-y: scroll;"><%=member.getMemo()%></textarea>
				<br>
				<%
					System.out.println("용식이 사진 : " + member.getProfileimg());
					}
				%>
			</form>

			<a href="../adminorder/memberManager">뒤로가기</a>&nbsp;&nbsp;


		</div>


		<!-- Footer -->
		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>