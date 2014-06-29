<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header">
	<nav id="nav-wrap" class="js-nav-primary">
		<h1 id="logo" class="title">
			<a id="logo-link" href="<%=request.getContextPath()%>/boardorder/list">PhotoFolio</a>
		</h1>
		
		<div class="info">
	        <div class="search">	<!-- title="제목 또는 크리에이터 검색"  -->
				<input type="text" id="search" name="search" value="" title="제목 또는 크리에이터 검색" style="color: rgb(160, 160, 160);" 
				onfocus="checkSearchAreaOnFocus(this);" onkeypress="checkSearchQuery(event, this.value);">
	            <a href="javascript:clickSearchQuery();" title="검색" class="btn">검색</a>
	        </div>
			<a href="<%=request.getContextPath()%>/boardorder/randomarticle" title="랜덤 일러스트레이션" class="btn_random">랜덤 일러스트레이션</a>
				<div id="cmt_noti" class="cmtnoti_info">
					<a href="#" id="cmt_noti_icon" class="btn_cmtnoti" title="댓글 알림">댓글 알림</a><span class="count"></span>
					<div class="cmtnoti_wrap">
						<span class="ic"></span>
						<div id="alertCommentList" class="cmtnoti_inn">
							<p class="noti">최근 1주일간의 알림입니다.</p>
							<div class="cmt_area blank"><p>새로운 댓글이나 언급이 없습니다.</p></div>
						</div>
					</div>
				</div>
				<!-- 로그인 전 -->
				<%
					if(session.getAttribute("id")==null){
				%>
					<div class="user_info" id="loginInfoEl">
						<a href="<%=request.getContextPath()%>/member/login.jsp" class="login">로그인</a>
					</div>
				<%}else{%>
				<!-- 로그인 후 -->
					<div class="user_info" id="loginInfoEl">
						<a href="<%=request.getContextPath()%>/memberorder/likeinfoprocess?id=<%=session.getAttribute("id")%>" class="profile" title="내 프로필">
							<span class="thmb">
							<img src="<%=request.getContextPath()%>/storage/profile/<%=session.getAttribute("profileimg")%>" width="32" height="32" alt="내 프로필" onerror="this.src='/img/no32_32.jpg'">
							</span>
							<span class="mask"></span>
						</a>
						<ul class="profile_lst" style="display: none;">
							<span class="ic"></span>
							<ul>
								<li>
									<a href="<%=request.getContextPath()%>/memberorder/modifyprocess">내 정보관리</a>
								</li>
								<li>
									<a href="<%=request.getContextPath()%>/memberorder/logoutprocess">로그아웃</a>
								</li>
							</ul>
						</ul>
					</div>
				<%}%>
				
					<script type="text/javascript">
						$(function(){
							$('#loginInfoEl .profile').on('mouseenter',function(){
								$('.profile_lst').css('display','block');
							});
							$('#loginInfoEl').on('mouseleave', function(){
								$('.profile_lst').css('display','none');
							});
							
							$('.btn_cmtnoti').on('click',function(e){
								e.preventDefault();
								alert('준비중입니다.');								
							});							
						});
					</script>
			<a href="<%=request.getContextPath()%>/boardorder/upload" class="btn_upload">업로드</a>
		</div>
	</nav>
	<div class="menuArea">
		<div class="menu">
			<span id="menu1" class="type active">menu1</span>
			<span id="menu2" class="type">menu2</span>
		</div>
	</div>
</div>

<!-- 네비공간 -->
<div id="spacezero"></div>