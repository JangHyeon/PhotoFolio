<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/common.css">
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="/include/header.jsp"/>

		<div id="main">
										
		</div>
		<!-- Footer -->
		<jsp:include page="/include/footer.jsp"/>

	</div>
</body>
</html>