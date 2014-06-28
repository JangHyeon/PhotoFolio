<%@page import="javax.websocket.Session"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.photofolio.DTO.Member"%>
<%@page import="com.photofolio.Action.ModifyAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="../css/common.css">
<%
String myid = (String)session.getAttribute("id");
ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
String id = request.getParameter("id");
String listid = (String)request.getAttribute("listid");
String check = (String) request.getAttribute("check");
%>
<style type="text/css">
	#likeyouview{display: none;}
	#likeme{float: left;}
	#likeyou{float: left;}

	
</style>
</head>
<body>
   <div id="page">
      <!-- Header -->
      
      <jsp:include page="../include/header.jsp"/>
      <div id="main">
	      <div>
	   
	      	<a href="../memberorder/likeinfoprocess?id=<%=id%>">내가 관심추가한 친구</a> ||<a href="../memberorder/likeinfo2process?id=<%=id%>">나를 관심 추가한 친구</a><br>
	         <div id="likemeview"  >
	          <hr>

	      
	       <%for(int i=0;i<list.size();i++){//내가 관심 추가한친구	%>
	    	
	    	이름:<%=list.get(i).getNickname()%>님 <br>
	    	이메일:<%=list.get(i).getEmail()%><br>
	    	 소개:<%=list.get(i).getMemo()%><br>
	    	  <%
	    	  if(check.equals("checkok") && id.equals(myid) ){
	    		  %>
	    		<a href="../memberorder/likeadd?id=<%=id%>&listid=<%=list.get(i).getId()%>">관심 추가</a>
	    		  <%
	    	  }
	    	  %>
	    	 <hr>
	    	 
	    	  
	     <% } %>
	      </div>
	 
	    
         </div>
         </div> 
	            
  
      
      <!-- Footer -->
      <jsp:include page="../include/footer.jsp"/>

   </div>
</body>
</html>