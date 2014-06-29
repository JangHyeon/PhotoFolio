<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="../css/join.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>

<script type="text/javascript">
$(function(){
   $('#idcheck').click(function(){
      var id = {id : $('#id').val()};
      $.ajax({
            url:"../memberorder/idcheckprocess",
            data:id,
            success:function(data){
            if(data == 0){
               alert('사용가능한 아이디입니다');
               $('#checkid').val(10);
            }else{
               alert('중복 아이디입니다');
            }
             },
             fail:function(){
             alert('접속실패');   
             }
         });
   });
   $('#nicknamecheck').click(function(){
      var nickname = {nickname : $('#nickname').val()};
      $.ajax({
            url:"../memberorder/nicknamecheckprocess",
            data:nickname,
            success:function(data){
            if(data == 0){
               alert('사용가능한 닉네임입니다');
               $('#checknickname').val(10);
            }else{
               alert('중복 닉네임입니다');
            }
             },
             fail:function(){
             alert('접속실패');   
             }
         });
   });
   
   $('#join').click(function(){
        if($('#checkid').val()==10&&$('#checknickname').val()==10){
           $('#joinform').attr('action','../memberorder/joinprocess').submit();   
        }else{
           if($('#checkid').val()<10){
              alert('아이디 중복체크를 해주세요');
           }else{
              alert('닉네임 중복체크를 해주세요');
           }
           
        }
            
      });
   
   
   
});
</script>




</head>
<body>
   <div id="page">
      <!-- Header -->
      
      <jsp:include page="../include/header.jsp"/>
      <div id="main">
      
      <div class="joinform">
      <form action="../memberorder/joinprocess" id="joinform" method="post">
      <h3>회원가입</h3>
      <br>
         아이디: <input type="text" id="id" name="id"><input id ="checkid"type="text" hidden="hideen">&nbsp;&nbsp;&nbsp;&nbsp;<button id="idcheck" class="loginbtn" type="button">중복체크</button><br>
         비밀번호 : <input type="password" name="pwd"><br>
         닉네임 : <input type="text" name="nickname"><input id ="checknickname"type="text" hidden="hideen">&nbsp;&nbsp;&nbsp;&nbsp;<button id="nicknamecheck" class="loginbtn" type="button">중복체크</button><br>
         휴대폰번호 : <input type="text" name="phone"><br>
         이메일 : <input type="text" name="email"><br>

         주소 : <input type="text" name="address"><br>
         <br>프로필이미지 <br> 
         <input type="image" src="WebContent/images/bg_thmb_blank3.gif">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="이미지 업로드" class="loginbtn" name="imgbtn"><br>
               프로필 <br>
<textarea rows="10" cols="30"name="profile" style="resize:none; overflow-y:scroll;"></textarea><br>
         
         </form>
         
        <br> <a id="join" class="loginbtn"><span>회원가입</span></a>&nbsp;&nbsp;

  </div>
      </div>
      
      
      <!-- Footer -->
      <jsp:include page="../include/footer.jsp"/>

   </div>
</body>
</html>