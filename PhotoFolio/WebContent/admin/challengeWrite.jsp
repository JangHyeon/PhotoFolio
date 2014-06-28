<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="../css/common.css">
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="../include/header.jsp" />

		<div id="main">
			<h2>챌린지관리(등록)</h2>
			<form action="../adminorder/ChallengeWrite" method="post">
				<div>
					<input type="text" size="80px"  name="subject" placeholder="챌린지명:"><br>
					<input type="text"  size="48px" name="startdate" placeholder="시작일 : 0000-00-00">
					-<input type="text"  size="48px" name="enddate" placeholder="종료일 : 0000-00-00"><br>
					<textarea name="content" rows="" cols="90px" placeholder="내용"></textarea><br>
					<input type="button" name="selectThumbnail" value="섬네일선택"> 
					<input type="button" name="selectImg" value="이미지선택"> 
					<br>
				</div>

				<div>
					엠블럼 사진 등록하는 것 일단보류<br> 
					<input type="text" name="emblem" placeholder="엠블럼명 :">
				</div>
				
				
				<div>
				<a href="../adminorder/challengeManeger">뒤로가기</a>
				<input type="submit" name="write" value="등록">
				
				</div>
			</form>
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>