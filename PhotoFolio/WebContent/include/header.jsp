<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header">
	<nav id="nav-wrap" class="js-nav-primary">
		<h1 id="logo" class="title">
			<a id="logo-link" href="/">PhotoFolio</a>
		</h1>
		
		<div class="info">
	        <div class="search">	<!-- title="제목 또는 크리에이터 검색"  -->
				<input type="text" id="search" name="search" value="" title="제목 또는 크리에이터 검색" style="color: rgb(160, 160, 160);" 
				onfocus="checkSearchAreaOnFocus(this);" onkeypress="checkSearchQuery(event, this.value);">
	            <a href="javascript:clickSearchQuery();" title="검색" class="btn">검색</a>
	        </div>
			<a href="javascript:moveRandomProject();" title="랜덤 일러스트레이션" class="btn_random">랜덤 일러스트레이션</a>
				<div id="cmt_noti" class="cmtnoti_info">
					<a href="javascript:member.login();" id="cmt_noti_icon" class="btn_cmtnoti" title="댓글 알림">댓글 알림</a><span class="count"></span>
					<div class="cmtnoti_wrap">
						<span class="ic"></span>
						<div id="alertCommentList" class="cmtnoti_inn">
							<p class="noti">최근 1주일간의 알림입니다.</p>
							<div class="cmt_area blank"><p>새로운 댓글이나 언급이 없습니다.</p></div>
						</div>
					</div>
				</div>
			<!-- 로그인 전 -->
			<div class="user_info" id="loginInfoEl">
				<a href="javascript:member.login();" class="login">로그인 / 회원가입</a>
			</div>
				
			<a href="../boardorder/upload" class="btn_upload">업로드</a>
		</div>
		<!-- 
		
	<div class="info">
	        <div class="search">	title="제목 또는 크리에이터 검색" 
				<input type="text" id="search" name="search" value="" title="제목 또는 크리에이터 검색" style="color: rgb(160, 160, 160);" onfocus="checkSearchAreaOnFocus(this);" onkeypress="checkSearchQuery(event, this.value);">
	            <a href="javascript:clickSearchQuery();" title="검색" class="btn">검색</a>
	        </div>
			<a href="javascript:moveRandomProject();" title="랜덤 일러스트레이션" class="btn_random">랜덤 일러스트레이션</a>
				[D] 댓글 알림 a클릭시 opn클래스 추가
				<div id="cmt_noti" class="cmtnoti_info opn">
				<a href="#" onclick="openCommentAlertLayer();" id="cmt_noti_icon" class="btn_cmtnoti" title="댓글 알림">댓글 알림</a><span class="count">0</span>
					<div class="cmtnoti_wrap">
						<span class="ic"></span>
						<div id="alertCommentList" class="cmtnoti_inn"><p class="noti">최근 1주일간의 알림입니다.</p>
						<div class="cmt_area blank"><p>새로운 댓글이나 언급이 없습니다.</p></div>
						</div>
					</div>
				</div>

				로그인 후
				<div class="user_info" id="loginInfoEl">
					<a href="http://grafolio.net/lkjlki" class="profile" title="내 프로필">
						<span class="thmb">
						<img src="http://img.phinf.grafolio.kr/20140617_46/1403001376220qcFYu_GIF/59410profileImage.gif?type=m32_32" width="32" height="32" alt="내 프로필" onerror="this.src='/img/no32_32.jpg'">
						</span>
						<span class="mask"></span>
					</a>
					<ul class="profile_lst" style="display: none;">
						<span class="ic"></span>
						<ul>
						<li><a href="https://grafolio.net/account/info.grfl">내 정보관리</a></li>
						
						<li><a href="javascript:member.logout();">로그아웃</a></li>
						</ul>
					</ul>
				</div>
				
				<a href="http://grafolio.net/uploader/form.grfl" class="btn_upload">업로드</a>
		</div> -->
	
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