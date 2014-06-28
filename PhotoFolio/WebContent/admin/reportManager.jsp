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
				<h2>신고관리</h2>
				<div>
					<table border="1">
						<tr>
							<th>신고된 게시물</th>
						</tr>
						<tr><td>a</td></tr>
						<tr><td>b</td></tr>
						<tr><td>c</td></tr>
						<tr><td>d</td></tr>
						
					</table>
				</div>
				
								<div>
					<table border="1">
						<tr>
							<th>신고된 댓글</th>
						</tr>
						<tr><td>a</td></tr>
						<tr><td>b</td></tr>
						<tr><td>c</td></tr>
						<tr><td>d</td></tr>
						
					</table>
				</div>	
				<a href="../admin/dashboard.jsp">목록</a>
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp"/>

	</div>
</body>
</html>