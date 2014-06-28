<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*"%>
<%@ page import="com.photofolio.DTO.Article" %>
<%@ page import="com.photofolio.DTO.Member" %>
<%@ page import="com.photofolio.DAO.AdminDao" %>

<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="../css/common.css">
 <script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">

$(function(){
	   $('#select').change(function(){
		   if($('#select option:selected').text() == '일반회원'){
			   $("#memberOK").show();
			      $("#creatorOK").hide();
		   }else{
			   $("#creatorOK").show();
			      $("#memberOK").hide();
		   };
	   });
	});

</script>
<style type="text/css">
 /* #memberOK{display: none;} */
</style>
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="../include/header.jsp"/>

		<div id="main">
				<h2>회원등급관리</h2>
	
				<select id="select">
					<option id="member" >일반회원</option>
					<option id="creator" selected="selected">크리에이터</option>
				</select>
				
					
					
<div id="memberOK">일반회원!!!								
				<br>
				<table>
					<tr>
						<th>회원아이디</th>
						<th>회원등급</th>
						<th>좋아요</th>
						<th>등급조정</th>
					</tr>

<%
	List memberLevelList = (List)request.getAttribute("memberLevelList");

			
	for(int i=0;i<memberLevelList.size();i++){
	Member member = (Member)memberLevelList.get(i);
%>
					
					<tr>
						<td><%=member.getNickname() %>(<%=member.getId()%>)</td>
						<td><%=member.getLevel() %></td>
						<td><%-- <%=member.getLike() %> --%></td>
						<td><a href="../adminorder/memberLevelUp?id=<%=member.getId() %>">등급업</a></td>
						
					</tr>
<%} %>
</table>					
</div>
				
				
						
<div id="creatorOK">크리에이터!!
	<br>
				<table>
					<tr>
						<th>회원아이디</th>
						<th>회원등급</th>
						<th>좋아요</th>
						<th>등급조정</th>
					</tr>
<%

	List creatorLevelList = (List)request.getAttribute("creatorLevelList");
	for(int i=0;i<creatorLevelList.size();i++){
	Member member = (Member)creatorLevelList.get(i);
%>
					
					<tr>
						<td><%=member.getNickname() %>(<%=member.getId()%>)</td>
						<td><%=member.getLevel() %></td>
						<td><%=member.getLike() %></td>
						<td><a href="../adminorder/creatorLevelDown?id=<%=member.getId() %>">강등</a></td>
						
					</tr>
<%} %>
				 
				 </table>
</div>
	
		<a href="../admin/dashboard.jsp">목록</a>
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp"/>

	</div>
</body>
</html>