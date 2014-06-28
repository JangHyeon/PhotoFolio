<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/common.css">
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="<%=path%>/include/header.jsp"/>

		<div id="main">
										
		</div>
		<!-- Footer -->
		<jsp:include page="<%=path%>/include/footer.jsp"/>

	</div>
</body>
</html>