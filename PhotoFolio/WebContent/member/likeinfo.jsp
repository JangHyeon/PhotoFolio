<%@page import="com.photofolio.DTO.Article"%>
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
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
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
String myid = (String)session.getAttribute("id");//세션에서 자신의 아이디를 불러온다
ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");//나를 관심 or 내가 관심한 친구 리스트
ArrayList<Member> info = (ArrayList<Member>)request.getAttribute("info");//해당 기준 아이디의 프로필 정보 
ArrayList<Article> Articlelist = (ArrayList<Article>)request.getAttribute("memberArticle");//해당 기준 아이디의 프로필 정보 

String id=  request.getParameter("id");//정보페이지의 접속 기준 아이디

String check = (String)request.getAttribute("check");//내가 관심or 나를 관심준 친구를 체크 
String profileimg = (String)session.getAttribute("profileimg");//세션에 담긴 프로필 이미지
String nickname = (String)session.getAttribute("nickname");//세션에 담긴 닉네임


%>



</head>
<body>
   <div id="page">
      <!-- Header -->
      
      <jsp:include page="../include/header.jsp"/>
      <div id="main">
     
      <div id="mylist"  >
      <center>
        <%for(int i=0; i<info.size();i++){// 프로필 출력 %>
        <img src="<%=request.getContextPath()%>/storage/profile/<%=info.get(i).getProfileimg()%>"style="width:180px;height:180px"><br>
        닉네임:<%=info.get(i).getNickname() %><br>
        지역:<%=info.get(i).getAddress() %><br>
       email:<%=info.get(i).getEmail() %><br>	
        프로필:<%=info.get(i).getMemo() %><br>
      	   <%if(!myid.equals(id)){//로그인한 아이디와 접속한 페이지 아이디가 다를경우 관심추가 버튼 추가; %>
 	      <a href="likeadd?id=<%=id%>&myid=<%=myid%>">관심추가</a><br>
		    	 <%} %>
		    	
        <%} %>
        <hr>
        	<a href="selectlist?id=<%=id%>"><b><%=id%>님의 게시물</b></a>||<a href="likeinfoprocess?id=<%=id%>"><b><%=id%>님이 관심 추가한 친구</b></a>||<a href="likeinfo2process?id=<%=id%>"><b><%=id%>을 관심 추가한 친구</b></a><br>
	    
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
		  <%if(myid.equals(id)&&check.equals("checkok")){//로그인한 아이디와 접속한페이지 아이디가 같고 메뉴가 나를 관심 추가한 친구인경우
		  	if(list.get(j).getCheckintreset() !=null){//나를 관심추가한 친구인경우
			 %>	 <button id="likedelete" onclick="location.href='likedelete?id=<%=list.get(j).getId()%>&myid=<%=id%>'" ><font color="yellow">★ </font>&nbsp;&nbsp;<font color="white">관심</font></button><br>
	
		  <% 	}else{//내가 관심 추가한 친구가 아닌경우%>
				<button id="likeadd" onclick="location.href='likeadd?id=<%=list.get(j).getId()%>&myid=<%=id%>'" ><font >★ </font>&nbsp;&nbsp;<font >관심</font></button><br>
				
		<%  }%>
		  <%}else if(myid.equals(id)&&check.equals("checkno")){//로그인한 본인 페이지이며 내가 관심추가한 친구인경우%>
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
