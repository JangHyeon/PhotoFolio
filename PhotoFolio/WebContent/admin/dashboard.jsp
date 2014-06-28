<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SamplePage</title>
<link type="text/css" rel="stylesheet" href="../css/common.css">
<style type="text/css">
	#main{
	width: 920px;
	height:900px;
	background-color: black;
	}

	#sidebar{
	width: 100px;
	height:700px;
	background-color :yellow;
	float: left;
	}
	
	

	#maintable{
	width: 700px;
	height:700px;
	background-color :green;
	float: left;
	padding: 20px;
	}

	#table1{
	width: 300px;
	height:300px;
	background-color: white;
	float: left;
	padding: 10px;
	margin: 10px;
	
	
	}
	#table2{
	width: 300px;
	height:300px;
	background-color: white;
	float: left;
	padding: 10px;
	margin: 10px;
	
	}
	#table3{
	width: 300px;
	height:300px;
	background-color: white;
	float: left;
	padding: 10px;
	margin: 10px;
	
	}
	#table4{
	width: 300px;
	height:300px;
	background-color: white;
	float: left;
	padding: 10px;
	margin: 10px;
	
	}

</style>
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="../include/header.jsp"/>

		<div id="main">
		
			
			<h1 align="center">대시보드</h1>
			<div id="sidebar">
				<ul>
					<li><a href="dashboard.jsp">대시보드</a></li>
					<li><a href="../adminorder/memberManager">회원관리</a></li>
					<li><a href="../adminorder/memberLevelManager">회원등급관리</a></li>
					<li><a href="../adminorder/reportManager">신고관리</a></li>
					<li><a href="../adminorder/challengeManeger">챌린지관리</a></li>
					<li><a href="../adminorder/emblemManeger">앰블럼관리</a></li>
					
				</ul>
			</div>
			<div id="maintable">
				<div id="table1">table1</div>
				<div id="table2">table2</div>
				<div id="table3">table3</div>
				<div id="table4">table4</div>
			</div>
			
			
			
					
							
									 
		</div>
		<!-- Footer -->
		<jsp:include page="../include/footer.jsp"/>

	</div>
</body>
</html>