<%@page import="javax.websocket.SendResult"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%
String id = (String)session.getAttribute("id"); 
if(id != null && !id.equals("")){
  response.sendRedirect("../Sample.jsp?id="+id);   
}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="../css/login.css">
</head>
<body>
   <div id="page">
      <!-- Header -->
      <jsp:include page="../include/header.jsp"/>

      <div id="main">
      <div class="loginform">
		<form name="loginform" action="../memberorder/logonprocess" method="post"> 
           <h3>로그인</h3><br>
             <div class="input_row" id="id_area">
					<span class="input_box">
				 <input type="text" id="id" name="id" tabindex="7" accesskey="L" placeholder="아이디" class="int" maxlength="25" value="">
					</span>
				</div>
				
	<div class="input_row" id="pw_area">
					<span class="input_box">
						<label for="pw" id="label_pw_area" class="lbl" style="display: block;"></label>
					 <input type="password" id="pwd" name="pwd" tabindex="8" placeholder="비밀번호" class="int" maxlength="16" onkeypress="capslockevt(event);getKeysv2();" onkeyup="checkShiftUp(event);" onkeydown="checkShiftDown(event);">
					</span>
				</div>
				        <button type="submit" name="loginbtn" id="loginbtn" class="loginbtn">로그인</button>
           </form>
           </div>
      </div>
      

				
      <!-- Footer -->
      <jsp:include page="../include/footer.jsp"/>

   </div>
</body>
</html>