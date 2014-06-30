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
#likeinfoArticleview{
	border: 2px outset ;
	padding: 5px;
	width: 200px;
	height:220px;
    float:left;
    margin: 10px;
}
#d{
clear: both;
}

#likeadd{
border: 1px outset;
width: 100px;
height:30px;

}
</style>
<%   
String myid = (String)session.getAttribute("id");//세션에서 자신의 아이디를 불러온다

ArrayList<Member> info = (ArrayList<Member>)request.getAttribute("info");//해당 기준 아이디의 프로필 정보 
ArrayList<Article> Articlelist = (ArrayList<Article>)request.getAttribute("memberArticle");//해당 기준 아이디의 프로필 정보 

String id=  request.getParameter("id");//정보페이지의 접속 기준 아이디

String check = (String)request.getAttribute("check");//내가 관심or 나를 관심준 친구를 체크 
String profileimg = (String)session.getAttribute("profileimg");//세션에 담긴 프로필 이미지
String nickname = (String)session.getAttribute("nickname");//세션에 담긴 닉네임


for(int ch=0;ch<Articlelist.size();ch++){
	
	System.out.println(Articlelist.get(ch).getSubject());
}

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
	              <div id="likeinfoArticle" >
	             
         <% for(int i=0; i<Articlelist.size();i++){// 섬네일 출력 %>

      <div id="likeinfoArticleview"> 
           <center>
       	<a href="<%=request.getContextPath()%>/boardorder/view?idx=<%=Articlelist.get(i).getIdx()%>"> <img src="<%=request.getContextPath()%>/storage/thumbnail/<%=Articlelist.get(i).getThumbnail()%>"style="width:180px;height:180px"></a><br>
             <%=Articlelist.get(i).getSubject() %><br>
         <%=Articlelist.get(i).getWritedate() %><br>
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
