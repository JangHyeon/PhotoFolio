<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
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
      <jsp:include page="../include/header.jsp"/>

      <div id="main">
		<form name="loginform" action="../memberorder/logonprocess" method="post"> 
		
    	<h3>로그인</h3>&nbsp;&nbsp;&nbsp;<br>
    	id:<input type="text" name="id"><br>
        pwd:<input type="password" name="pwd"><br>
        <input type="checkbox" name="loginchk"> 로그인 유지 || <a href="#"> 비밀번호 찾기</a>
        
        <input type="submit" value="로그인">
           </form>
      </div>
      
   
      <!-- Footer -->
      <jsp:include page="../include/footer.jsp"/>

   </div>
</body>
</html>