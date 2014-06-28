<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="css/common.css">
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="include/header.jsp"/>

		<div id="main">
			<div>
				
<%
	System.out.println("asd");
    Connection conn=null;
    try{
        Context init = new InitialContext();
        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
        conn = ds.getConnection();
        out.println("Success!!!");
    }catch(Exception e){
        out.println("Failure!!!");
        e.printStackTrace();
    }
%>
			</div>
			
		</div>
		<!-- Footer -->
		<jsp:include page="include/footer.jsp"/>

	</div>
</body>
</html>