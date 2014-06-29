<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="css/common.css">

</head>
<body>
   <div id="page"> 
      <!-- Header -->
      <jsp:include page="include/header.jsp"/>

       <div id="main">
         <div>
  <%
  String id = (String)session.getAttribute("id"); 
  String nickname = (String)session.getAttribute("nickname");  
  
    if(id != null){
    	  int lvl = (Integer)session.getAttribute("lvl");
    	%>
  	<a href="./memberorder/likeinfoprocess?id=<%=id%>"><%=nickname%></a><%if(lvl>0){%><b>크리에이터 님 반갑습니다</b><%}else{%><b>님 반갑습니다</b><%}%><br>
  	<a href="./memberorder/modifyprocess">정보수정</a>||<a href="./memberorder/logoutprocess">로그아웃</a><%}else{%>
	  <a href="./member/login.jsp" >로그인</a>||<a href="./member/join.jsp">회원가입</a>
	  <%}%>    

       

         </div>
         
      </div>
      <!-- Footer -->
      <jsp:include page="include/footer.jsp"/>

   </div>
</body>
</html>