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
<link type="text/css" rel="stylesheet" href="../css/login.css">
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="../include/header.jsp" />

		<div id="main" align="center">
			<div class="loginform">
				<form name="loginform" action="<%=request.getContextPath()%>/adminorder/login" method="post">
					<h3>Admin</h3>
					<div class="input_row" id="id_area">
						<span class="input_box"> <input type="text" id="id" name="id" tabindex="7" accesskey="L" placeholder="아이디" class="int" maxlength="25" value=""></span>
					</div>
					<div class="input_row" id="pw_area">
						<span class="input_box"> 
							<label for="pw" id="label_pw_area" class="lbl" style="display: block;"></label> 
							<input type="password" id="pwd" name="pwd" tabindex="8" placeholder="비밀번호" class="int" maxlength="16">
						</span>
					</div>
					<button type="submit" name="loginbtn" id="loginbtn" class="loginbtn">로그인</button>
				</form>
			</div>
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp" />

	</div>
</body>
</html>