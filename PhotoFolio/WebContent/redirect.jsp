<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String msg = (String)request.getAttribute("msg");
   String url = (String)request.getAttribute("url");
   HttpSession session1 = request.getSession();
   int result = (Integer)request.getAttribute("result");
   if(result >0){%>
   <script type="text/javascript">
 
      alert("<%=msg%>성공하였습니다");
      location.href="<%=url%>";
      </script>  
   <%}else{
	  
   %>
   <script type="text/javascript">
       alert("<%=msg%>실패하였습니다");
      location.href="<%=url%>";
      </script>  
   <%}%>