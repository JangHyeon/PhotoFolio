<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<jsp:include page="../include/header.jsp"/>

		<div id="main">
				<form action="../adminorder/login" method="post">
					<h3>포토폴리오</h3>
					로그인<br>
					<input type="text" name="id"  placeholder="아이디"><br>
					<input type="password" name="pwd" placeholder="비밀번호"><br>
					<input type="submit" value="로그인">
				</form>	
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp"/>

	</div>
</body>
</html>