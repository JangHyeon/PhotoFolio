<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<!-- Latest compiled and minified JavaScript -->
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=path%>/js/jquery.fileupload.js"></script>
<link rel="stylesheet" href="<%=path%>/css/jquery.fileupload.css">

<link href="<%=path%>/css/boarduploader.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="../css/common.css">
<script type="text/javascript">
	$(function() {

		//1. 썸네일 업로드
		$('#thumbnail_upFile').fileupload(
				{
					url : '../memberorder/profileupload',
					dataType : 'json',
					//replaceFileInput: false,
					//dropZone:$(''),

					add : function(e, data) {
						var validFlag = true;
						var uploadFile = data.files[0];

						if (!(/png|jpe?g|gif/i).test(uploadFile.name)) {
							alert('png, jpg, gif 만 가능합니다');
							validFlag = false;
						} else if (uploadFile.size > 5 * 1024 * 1024) { // 5mb
							alert('파일 용량은 5메가를 초과할 수 없습니다.');
							validFlag = false;
						} else {
							var _URL = window.URL || window.webkitURL;
							var img = new Image();
							img.onload = function() {
								if (this.width < 100 || this.height < 100) {
									alert('100 x 100 보다 큰 이미지를 선택해 주세요.');
									validFlag = false;
								}
								_URL.revokeObjectURL(img.src);

								if (!validFlag) {
									// <input> 초기화 코드
									data.reset();
								} else {
									data.submit();
								}
							};
							img.src = _URL.createObjectURL(uploadFile);
						}
					},
					done : function(e, data) {
						$('#thumbnailImg').val(data.result.file_name);
						$('#thumbnailImgview').attr('src',
								data.result.file_path + data.result.file_name);
						console.log("업로드 성공");
					},
					fail : function() {
						alert("서버와 통신 중 문제가 발생했습니다");
					}
				});

		//2. 이미지 업로드
		$('#Img_upFile').fileupload(
				{
					url : '../memberorder/profileupload',
					dataType : 'json',
					//replaceFileInput: false,
					//dropZone:$(''),

					add : function(e, data) {
						var validFlag = true;
						var uploadFile = data.files[0];

						if (!(/png|jpe?g|gif/i).test(uploadFile.name)) {
							alert('png, jpg, gif 만 가능합니다');
							validFlag = false;
						} else if (uploadFile.size > 5 * 1024 * 1024) { // 5mb
							alert('파일 용량은 5메가를 초과할 수 없습니다.');
							validFlag = false;
						} else {
							var _URL = window.URL || window.webkitURL;
							var img = new Image();
							img.onload = function() {
								if (this.width < 100 || this.height < 100) {
									alert('100 x 100 보다 큰 이미지를 선택해 주세요.');
									validFlag = false;
								}
								_URL.revokeObjectURL(img.src);

								if (!validFlag) {
									// <input> 초기화 코드
									data.reset();
								} else {
									data.submit();
								}
							};
							img.src = _URL.createObjectURL(uploadFile);
						}
					},
					done : function(e, data) {
						$('#Img').val(data.result.file_name);
						$('#Imgview').attr('src',
								data.result.file_path + data.result.file_name);
						console.log("업로드 성공");
					},
					fail : function() {
						alert("서버와 통신 중 문제가 발생했습니다");
					}
				});

		//2. 앰블럼이미지 업로드
		$('#emblemImg_upFile').fileupload(
				{
					url : '../memberorder/profileupload',
					dataType : 'json',
					//replaceFileInput: false,
					//dropZone:$(''),

					add : function(e, data) {
						var validFlag = true;
						var uploadFile = data.files[0];

						if (!(/png|jpe?g|gif/i).test(uploadFile.name)) {
							alert('png, jpg, gif 만 가능합니다');
							validFlag = false;
						} else if (uploadFile.size > 5 * 1024 * 1024) { // 5mb
							alert('파일 용량은 5메가를 초과할 수 없습니다.');
							validFlag = false;
						} else {
							var _URL = window.URL || window.webkitURL;
							var img = new Image();
							img.onload = function() {
								if (this.width < 100 || this.height < 100) {
									alert('100 x 100 보다 큰 이미지를 선택해 주세요.');
									validFlag = false;
								}
								_URL.revokeObjectURL(img.src);

								if (!validFlag) {
									// <input> 초기화 코드
									data.reset();
								} else {
									data.submit();
								}
							};
							img.src = _URL.createObjectURL(uploadFile);
						}
					},
					done : function(e, data) {
						$('#emblemImg').val(data.result.file_name);
						$('#emblemImgview').attr('src',
								data.result.file_path + data.result.file_name);
						console.log("업로드 성공");
					},
					fail : function() {
						alert("서버와 통신 중 문제가 발생했습니다");
					}
				});

	});
</script>




</head>
<body>
	<div id="page">
		<!-- Header -->

		<jsp:include page="../include/header.jsp" />
		<div id="main" align="center">
			<h2>챌린지관리(등록)</h2>
			<form action="../adminorder/ChallengeWrite" method="post">
				<div>
					<input type="text" size="80px" name="subject" placeholder="챌린지명:"><br>
					<input type="text" size="48px" name="startdate"
						placeholder="시작일 : 0000-00-00"> -<input type="text"
						size="48px" name="enddate" placeholder="종료일 : 0000-00-00"><br>
					<textarea name="content" rows="" cols="90px" placeholder="내용"></textarea>
					<br> 썸네일 <input id="thumbnailImg" name="thumbnailImg"
						type="hidden"> <input id="thumbnailImgview" type="image"
						src="img">
					<!-- 부트 스트랩 적용 input버튼 -->
					<!-- The fileinput-button span is used to style the file input field as button -->
					<span class="btn btn-success fileinput-button"> <i
						class="glyphicon glyphicon-plus"></i> <span>썸네일 선택</span> <!-- The file input field used as target for the file upload widget -->
						<input id="thumbnail_upFile" type="file" name="files[]" multiple />
					</span> 이미지 <input id="Img" name="Img" type="hidden"> <input
						id="Imgview" type="image" src="img">
					<!-- 부트 스트랩 적용 input버튼 -->
					<!-- The fileinput-button span is used to style the file input field as button -->
					<span class="btn btn-success fileinput-button"> <i
						class="glyphicon glyphicon-plus"></i> <span>이미지 선택</span> <!-- The file input field used as target for the file upload widget -->
						<input id="Img_upFile" type="file" name="files[]" multiple />
					</span> <br>
				</div>

				<hr>
				<!-- 앰블럼 -->
				<div>
					앰블럼이미지 <input id="emblemImg" name="Img" type="hidden"> <input
						id="emblemImgview" type="image" src="img">
					<!-- 부트 스트랩 적용 input버튼 -->
					<!-- The fileinput-button span is used to style the file input field as button -->
					<span class="btn btn-success fileinput-button"> <i
						class="glyphicon glyphicon-plus"></i> <span>앰블럼이미지 선택</span> <!-- The file input field used as target for the file upload widget -->
						<input id="emblemImg_upFile" type="file" name="files[]" multiple />
					</span> <br> <input type="text" name="emblem" placeholder="엠블럼명 :">
				</div>


				<div>
					<a href="../adminorder/challengeManeger">뒤로가기</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" name="write" value="등록">

				</div>
			</form>

		</div>


		<!-- Footer -->
		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>