<%@page import="java.util.ArrayList"%>
<%@page import="com.photofolio.DTO.Member"%>
<%@page import="com.photofolio.Action.MemberModifyAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="../css/common.css">

<style type="text/css">
#memberinfo{
	border: 2px outset ;
	padding: 5px;
	width: 200px;
	height:200px;
    float:left;
    margin: 10px;
	

}
#d{
clear: both;
}
#likedelete{
border: 1px outset;
border-color: green ;
background-color: green;
width: 100px;
height:30px
}
#likeadd{
border: 1px outset;
width: 100px;
height:30px;

}
</style>
<%   
String myid = (String)session.getAttribute("id");
ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
ArrayList<Member> info = (ArrayList<Member>)request.getAttribute("info");

String id=  request.getParameter("id"); 

String check = (String)request.getAttribute("check");
String profileimg = (String)session.getAttribute("profileimg");
String nickname = (String)session.getAttribute("nickname");

%>



</head>
<body>
   <div id="page">
      <!-- Header -->
      
      <jsp:include page="../include/header.jsp"/>
      <div id="main">
     
      <div id="mylist"  >
      <center>
        <%for(int i=0; i<info.size();i++){
        
        
        
        %>
        <img src="<%=request.getContextPath()%>/storage/profile/<%=info.get(i).getProfileimg()%>"style="width:180px;height:180px"><br>
        닉네임:<%=info.get(i).getNickname() %><br>
        지역:<%=info.get(i).getAddress() %><br>
       email:<%=info.get(i).getEmail() %><br>	
        프로필:<%=info.get(i).getMemo() %><br>
      	   <%if(!myid.equals(id)){%>
 	      <a href="likeadd?id=<%=id%>&myid=<%=myid%>">관심추가</a><br>
		    	 <%} %>
		    	
        <%} %>
        <hr>
        	<a href="likeinfoprocess?id=<%=id%>"><b><%=id%>님이 관심 추가한 친구</b></a>||<a href="likeinfo2process?id=<%=id%>"><b><%=id%>을 관심 추가한 친구</b></a><br>
	    
      </center>
      </div>	    
	              <div id="likemeview" >
	             
         <%for(int j=0;j<list.size();j++){
         

         
         %>
      <div id="memberinfo"> 
           <center>
       			<img src="<%=request.getContextPath()%>/storage/profile/<%=list.get(j).getProfileimg()%>" style="width: 50px;height: 50px" ><br>
		  	이름:<a href="likeinfo2process?id=<%=list.get(j).getId() %>"><%=list.get(j).getNickname()%>님 </a><br>
		      	<%=list.get(j).getEmail()%><br>
		    	 소개:<%=list.get(j).getMemo()%><br><br>
		  <%if(myid.equals(id)&&check.equals("checkok")){
		  	if(list.get(j).getCheckintreset() !=null){
			 %>	 <button id="likedelete" onclick="location.href='likedelete?id=<%=list.get(j).getId()%>&myid=<%=id%>'" ><font color="yellow">★ </font>&nbsp;&nbsp;<font color="white">관심</font></button><br>
	
		  <% 	}else{%>
				<button id="likeadd" onclick="location.href='likeadd?id=<%=list.get(j).getId()%>&myid=<%=id%>'" ><font >★ </font>&nbsp;&nbsp;<font >관심</font></button><br>
				
		<%  }%>
		  <%}else if(myid.equals(id)&&check.equals("checkno")){ %>
		<button id="likedelete" onclick="location.href='likedelete?id=<%=list.get(j).getId()%>&myid=<%=id%>'" ><font color="yellow">★ </font>&nbsp;&nbsp;<font color="white">관심</font></button><br>
		      
		 <%}%>
		    </center>
	 	</div>
	       <%} %>
	      <div id="d"></div>
	         
	 
	        
	    
         </div>
         </div> 
	            
  
      
      <!-- Footer -->
      <jsp:include page="../include/footer.jsp"	/>

   </div>
</body>
</html>