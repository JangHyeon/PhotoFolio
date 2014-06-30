<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String id = (String)session.getAttribute("id"); 
if(id != null && !id.equals("")){
  response.sendRedirect("../member/login.jsp?id="+id);	
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=path%>/js/jquery.fileupload.js"></script>
<link rel="stylesheet" href="<%=path%>/css/jquery.fileupload.css">

<link href="<%=path%>/css/boarduploader.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="../css/common.css">
<script type="text/javascript">
$(function(){
	$('#idcheck').click(function(){
		//유효성 체크
		if($('#id').val() == ""){
			alert("id를 입력하세요");
			$('#id').focus();
			return;
		}else{
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
		};
	});
	$('#nicknamecheck').click(function(){
		//유효성 체크
		if($('#nickname').val() == ""){
			alert("nickname을 입력하세요");
			$('#nickname').focus();
			return;
		}else{
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
		};
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
	
	$('#profile_upFile').fileupload({
        url : '../memberorder/profileupload', 
        dataType: 'json',
        //replaceFileInput: false,
        //dropZone:$(''),
        
        add: function(e, data){
            var validFlag = true;
            var uploadFile = data.files[0];

            if (!(/png|jpe?g|gif/i).test(uploadFile.name)) {
                alert('png, jpg, gif 만 가능합니다');
                validFlag = false;   
            }else if (uploadFile.size > 5*1024*1024) { // 5mb
                alert('파일 용량은 5메가를 초과할 수 없습니다.');
                validFlag = false;
            }else{
                var _URL = window.URL || window.webkitURL; 
				var img = new Image();
		        img.onload = function() {
		        	if (this.width < 100 || this.height < 100){
		                alert('100 x 100 보다 큰 이미지를 선택해 주세요.');
		                validFlag = false;
		        	}
		        	_URL.revokeObjectURL(img.src);
		        	
		        	if (!validFlag) {
	                    // <input> 초기화 코드
	                    data.reset();
	                }else{
	                	data.submit();
	                }
		        };
		        img.src = _URL.createObjectURL(uploadFile);
            }
        },
        done: function (e, data) {
        	$('#profileimg').val(data.result.file_name);
        	$('#profileimgview').attr('src',data.result.file_path+data.result.file_name);
        	console.log("업로드 성공");
        },
        fail: function(){
            alert("서버와 통신 중 문제가 발생했습니다");
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
      
      
      <form action="../memberorder/joinprocess" id="joinform" method="post">
         아이디: <input type="text" id="id" name="id"><input id ="checkid"type="text" hidden="hideen"><button id="idcheck" type="button">중복체크</button><br>
         비밀번호 : <input type="password" name="pwd"><br>
         닉네임 : <input type="text" name="nickname"><input id ="checknickname"type="text" hidden="hideen"><button id="nicknamecheck" type="button">중복체크</button><br>
         휴대폰번호 : <input type="text" name="phone"><br>
         이메일 : <input type="text" name="email"><br>

         주소 : <input type="text" name="address"><br>
         프로필이미지 <br> 
        <input id="profileimg"name="profileimg" type="hidden">
         <input id="profileimgview" type="image" src="img">
         <!-- 부트 스트랩 적용 input버튼 -->
						<!-- The fileinput-button span is used to style the file input field as button -->
						<span class="btn btn-success fileinput-button">
					        <i class="glyphicon glyphicon-plus"></i>
					        <span>이미지 선택</span>
					        <!-- The file input field used as target for the file upload widget -->
					        <input id="profile_upFile" type="file" name="files[]" multiple/>
					    </span>
					    <br>
               프로필 <br>
<textarea rows="10" cols="30"name="memo" style="resize:none; overflow-y:scroll;"></textarea><br>
         
         </form>
         
         <a id="join">회원가입</a>&nbsp;&nbsp;

  
      </div>
      
      
      <!-- Footer -->
      <jsp:include page="../include/footer.jsp"/>

   </div>
</body>
</html>