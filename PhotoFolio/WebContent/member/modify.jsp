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
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">
<%int lvl = (Integer)session.getAttribute("lvl");

Member dto =  (Member)request.getAttribute("dto");
%>

$(function(){
	$('#modifybtn').click(function(){
		$("#modify").show();
		$("#profilemodify").hide();
		$("#createmodify").hide();
	});
$('#profilemodifybtn').click(function(){
		$("#profilemodify").show();
		$("#modify").hide();
		$("#createmodify").hide();
	});
$('#creatormodifybtn').click(function(){
	$("#profilemodify").hide();
	$("#modify").hide();
	$("#createmodify").show();
});


});
</script>
<style type="text/css">
	#profilemodify{display: none;}
	#modifybtn{float: left;}
	#profilemodifybtn{float: left;}
	#createmodify{display: none;}
	
</style>
</head>
<body>
   <div id="page">
      <!-- Header -->
      
      <jsp:include page="../include/header.jsp"/>
      <div id="main">
	      <div>
	      	<div id="modifybtn">계정정보 수정 || </div><div id="profilemodifybtn" >프로필정보 수정  </div><%if(lvl>0){ %><div id="creatormodifybtn"> || 크리에이터 프로필 수정</div><%}%>
	      </div><br>
	      <form name="modifyform" action="../memberorder/editprocess" method="post"> 
	     <div id="modify">
	           닉네임 : <input type="text" name="nickname"value="<%=dto.getNickname()%>"><br>
	         휴대폰번호 : <input type="text" name="phone" value="<%=dto.getPhone()%>"><br>
	         이메일 : <input type="text" name="email" value="<%=dto.getEmail()%>"><br>
	      </div>
	           <div id="profilemodify">
         주소 : <input type="text" name="address"value="<%=dto.getAddress()%>"><br>
    <input type="hidden" name="profileimg"value="<%=dto.getProfileimg()%>">
         프로필 : <input type="text" name="memo" value="<%=dto.getMemo()%>"><br>
	      </div>
	           <div id="createmodify">
         hompage : <input type="text" name="homepage" value="<%=dto.getHomepage()%>"><br>
       cmemo : <input type="text" name="cmemo" value="<%=dto.getCmemo()%>"><br>
 	   history: <input type="text" name="history" value="<%=dto.getHistory()%>"><br>
	      </div>
	         </form>  
	            <a href="javascript:modifyform.submit()">수정하기</a>&nbsp;&nbsp;
	         <a href="javascript:modifyform.reset()">다시작성</a><br>
	         <a href="../memberorder/deleteprocess">회원탈퇴</a>  
  
         
         </div> 
	            
  
      
      <!-- Footer -->
      <jsp:include page="../include/footer.jsp"/>

   </div>
</body>
</html>
