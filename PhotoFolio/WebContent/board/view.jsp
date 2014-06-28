<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.photofolio.DTO.Article" %>
<%@ page import="com.photofolio.DTO.Image" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>타이틀 :: PhotoFolio</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<link type="text/css" rel="stylesheet" href="<%=path%>/css/common.css">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/boardview.css">
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="/include/header.jsp" />

		<div class="tit_wrap">
			<div class="tit_area">
				<div class="tit" id="titDiv">
					<h3>${article.subject}</h3>
					<span class="name">by <a href="<%=path%>/memberorder/likeinfoprocess?id=${member.id}">${member.nickname}</a></span>
					<a href="<%=path%>/memberorder/likeinfoprocess?id=${member.id}" class="author">
						<span class="thmb">
							<img src="<%=path %>/storage/profile/${member.profileimg}"
							width="64" height="64" alt="${member.nickname}" onerror="this.src='../images/no_profile_64.gif'">
							<span class="mask"></span>
					</span>
					</a>
				</div>
				<!-- badge -->
				<div class="info">
					<p class="count">
						<span title="조회" class="hit">
							<strong>조회</strong>
							<em id="readCountEl">${article.count}</em>
						</span> <span title="좋아요" class="like">
							<strong>좋아요</strong>
							<em id="recommendCountEl">${article.like}</em>
						</span> 
						<span title="댓글수" class="reply">
							<strong>댓글수</strong>
							<em id="commentCountEl">${replycount}</em>
						</span>
					</p>
				</div>
			</div>
		</div>
		<div class="bdr"></div><div class="bdr shadow"></div>
		<c:choose>
			<c:when test="${article.secret==1}">
			<!-- [비공개] -->
			<div class="spot_noti">
				<div><span class="ic closed"></span>비공개로 설정된 게시물 입니다.</div>
			</div>
			</c:when>
			<c:when test="${article.secret==2}">
			<!-- [관리자가 차단] -->
			<div class="spot_noti">
				<div><span class="ic closed"></span>관리자가 차단한 게시물 입니다.</div>
			</div>
			</c:when>
		</c:choose>		
		<div id="main">
			
			<div class="article">
				<div class="contents">
					<!-- 이미지 반복 -->
					<c:forEach var="image" items="${imageList}">
						<p class="img"><img src="<%=path %>/storage/image/${image.file_name}" alt="${image.subject}" onerror="this.src='../images/no896_672.jpg'"></p>
						<p class="title"><strong>${image.subject}</strong></p>
						<p class="txt">${image.content}</p>
					</c:forEach>
					<!-- 이미지 반복끝 -->
					<!-- 태그 및 정보 -->
					<div class="info_area">
						<dl class="tag">
						<dt><span>태그</span></dt>
							<dd>
								<a href="/search.grfl?query=태그1&amp;mode=tag">태그1</a>,
								<a href="/search.grfl?query=태그2&amp;mode=tag">태그2</a>
							</dd>
						</dl>
						<div class="inn">
							<dl class="copyright">
								<dt><span>© Copyright</span></dt>
								<dd>본 콘텐츠의 저작권은 제공처에 있으며 이를 무단 사용시 저작권법 등에 따라 법적 책임을 질 수 있습니다.</dd>
							</dl>
							<p class="date">
								<span class="blind">등록일:</span>
								<fmt:formatDate value="${article.writedate}" pattern="yyyy.MM.dd" />
							</p>
							<p class="set" id="projectUserArea">
								<!-- 수정 삭제 토글 -->
								<c:if test="${sessionScope.id==article.id}">
								<a id="projectModifyForm" href="/uploader/modifyForm.grfl?projectNo=24386&amp;ctstNo=0">수정</a> 
								<span id="projectModifyFormBar" class="bar">|</span>
								<a id="projectDeleteForm" href="javascript:deleteProject();">삭제</a> 
								<span id="projectDeleteFormBar" class="bar">|</span>
								</c:if>
								<a id="openReportProjectButton" href="javascript:openProjectReport('${article.idx}');">신고</a>
							</p>
						</div>
					</div>
					<!-- 태그 및 정보끝 -->
					<!-- 좋아요 -->
					<div class="btn_area">
						<div class="btn_inn">
							<div class="like_uio">
								<a id="likeButton" href="javascript:alertDontLikeMyProject();" class="btn_circle like _like_link">
								<span class="ic"><span>
								</span></span>좋아요<em id="likeCount">${article.like}</em></a>
								<span id="likeSpan" class="btn_circle like _like" style="display: none;"></span>
								<p id="fadeAlert" class="ly_noti" style="display:none;">
									<span class="ct">이미 좋아요 했습니다.<span class="ic"></span></span>
								</p>
								<p id="fadeAlertMyProject" class="ly_noti" style="display:none;">
									<span class="ct">나의 작품은 좋아요 할 수 없습니다.<span class="ic"></span></span>
								</p>
							</div>
						</div>
					</div>
					<!-- 좋아요끝 -->
					<!-- 댓글 -->
					
					<div class="comment" id="commentInfo">
						<div class="comment_inp">
							<form action="#" method="post">
								<fieldset>
									<legend>댓글달기</legend>
										<label for="commentContent" id="comment_write" class="blind">댓글달기</label>
										<div class="cmt_wrap">
											<textarea id="commentContent" cols="80" rows="20" style="resize: none;"></textarea>
										</div>
										<a id="registCommentButton" href="javascript:commentWrite(${article.idx}, '${sessionScope.id}', '${sessionScope.nickname}', '${sessionScope.profileimg}');" class="btn_ty2">댓글달기</a>
								</fieldset>
							</form>
							<p class="dsc">댓글은 1,000자까지 작성할 수 있으며 주제와 무관한 댓글, 악플은 삭제될 수 있습니다. <a href="/댓글 운영정책링크" target="_blank">댓글 운영원칙 보기</a></p>
						</div>
						<!-- Comment List -->
						<ul class="comment_list" id="commentList">
							<!-- 댓글 반복 -->
							<c:forEach var="reply" items="${replyList}">
							<li id="comment_${reply.reply_idx}">
								<a href="<%=path%>/memberorder/likeinfoprocess?id=${reply.id}" class="author">
									<span class="thmb">
										<img src="<%=path%>/storage/profile/${reply.profileimg}" width="32" height="32" alt="${reply.nickname}" onerror="this.src='../images/no_profile_32.gif'"><span class="mask"></span>
									</span>
								</a>
								<div class="info">
									<a href="/${reply.id}" class="name">${reply.nickname}(${reply.id})</a>
									<span class="date"><fmt:formatDate value="${reply.writedate}" pattern="yyyy.MM.dd HH.mm" /></span>
									<!-- 삭제, 신고 토글 -->
									<c:if test="${sessionScope.id==reply.id}">
									<a href="#" class="del">삭제</a>
									</c:if>
									
									<span class="rt">
									<c:if test="${reply.report!=0}">
									<Strong>${reply.report}</Strong>
									</c:if>
									<c:if test="${sessionScope.id!=reply.id}">
									<a href="#" class="btn_report">신고</a>
									</c:if>
									</span>
								</div>
								<p class="txt">${reply.content}</p>
							</li>
							</c:forEach>
							<!-- 댓글반복끝 -->
						</ul>
						<!-- //Comment List -->
						<a href="javascript:moreCommentList();" id="moreCommentListButton" class="btn_ty2" style="display: none;">20개 더보기</a>
						<input id="currentRow" type="hidden" value="20">
						<input id="maxCommentCount" type="hidden" value="0">
					</div>
					<!-- 댓글끝 -->	
				</div>
			</div>
			
						<button id="opener">open the dialog</button>
 
<div id="dialog">
 <p>I'm a dialog</p>
 <br>
 <button id="close_btn">close</button>
</div>
			
		</div>
			
			
<script type="text/javascript">
//게시물 신고
var openProjectReport = function(idx){
	$.post(
        	"../boardorder/articlereport",
        	{
        		idx : idx
        	},
        	function(returndata){ 
        		if(returndata==0){
        			alert("신고에 실패했습니다\n다시 시도해 주세요");
        		}else{
        			$('#commentContent').val("");
        			$('#commentList').prepend(returndata);
        		}	         				
        	}
        ).fail(function(){
               alert("서버와 통신 중 문제가 발생했습니다");
        });
};
			
			
			
			
					//댓글 등록
					var commentWrite = function(idx, id, nickname, profileimg){
						$.post(
				        	"../boardorder/replywrite",
				        	{
				        		idx : idx,
				        		id : id,
				        		nickname : nickname,
				        		content : $('#commentContent').val(),
				        		profileimg : profileimg
				        	},
				        	function(returndata){ 
				        		if(returndata==0){
				        			alert("댓글 등록에 실패했습니다\n다시 시도해 주세요");
				        		}else{//성공시 댓글 동적 생성
				        			$('#commentContent').val("");
				        			$('#commentList').prepend(returndata);
				        		}	         				
				        	}
				        ).fail(function(){
				               alert("서버와 통신 중 문제가 발생했습니다");
				        });
					};
					
			        //댓글 삭제
			        $('#commentList').on('click','.del',function(e){
			        	e.preventDefault();
			        	
			        	var del = $(this).parent().parent();
			        	var index = $('li').index(del);
			        	var reply_idx = del.attr('id').substring(8);
			        	
			        	$.post(
							   	"../boardorder/replydelete",
							   	{
							   		reply_idx : reply_idx
							   	},
							   	function(returndata){ 
							 		if(returndata==0){
							   			alert("댓글 삭제에 실패했습니다\n다시 시도해 주세요");
							   		}else{
							        	$('#commentList').children('li').eq(index).remove();
							   		}	         				
							   	}
							).fail(function(){
							alert("서버와 통신 중 문제가 발생했습니다");
						});
			        });
			     
			        
			      //댓글 신고
			      $('#commentList').on('click','.btn_report',function(e){
				      	e.preventDefault();

				        var del = $(this).parent().parent().parent();
				        var index = $('li').index(del);
				        var reply_idx = del.attr('id').substring(8);

				        $.post(
						   	"../boardorder/replyreport",
						   	{
						   		reply_idx : reply_idx
						   	},
						   	function(returndata){ 
						 		if(returndata==0){
						   			alert("댓글 신고에 실패했습니다\n다시 시도해 주세요");
						   		}else{
						        	var report = $('#commentList').children('li').eq(index).find('strong');
						        	var reportcount = report.text();
						        	alert('신고되었습니다.\n취소는 다음 프로젝트에서...');
						        	report.text(reportcount*1+1);
						   		}	         				
						   	}
						).fail(function(){
						alert("서버와 통신 중 문제가 발생했습니다");
						});
			        });
			</script>

		<!-- Footer -->
		<jsp:include page="/include/footer.jsp" />

	</div>
</body>
</html>