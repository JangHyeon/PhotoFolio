<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 :: PhotoFolio</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=path%>/js/jquery.fileupload.js"></script>
<link rel="stylesheet" href="<%=path%>/css/jquery.fileupload.css">

<script src="<%=path%>/cropper/cropper.js"></script>
<link  href="<%=path%>/cropper/cropper.css" rel="stylesheet">

<link href="<%=path%>/css/common.css" rel="stylesheet">
<link href="<%=path%>/css/boarduploader.css" rel="stylesheet">
</head>
<body>
	<div id="page">
		<!-- Header -->
		<jsp:include page="/include/header.jsp" />

		<div id="main">
			<div class="write_ty1">
			<!-- 개요 -->
			<div class="section_summary">
				<div class="tit">
					<h4><span>01</span>개요</h4>
					<p>제목과 소개글을 입력해 주세요.<br>
					친절하고 상세한 소개글이<br>
					더 많은 대중의 공감을 얻을 수 있습니다.</p>
				</div>
				<div class="form">			
					<!-- Article 입력 정보 -->
					<form action="<%=path%>/boardorder/view" method="get" id="processform">
						<input type="hidden" id="idx" name="idx">
					</form>
					
					<!-- 섬네일 -->
					<input type="hidden" id="thumbnail" name="thumbnail">	
	
					<fieldset>
						<legend>개요</legend>
						<p class="notice">*필수항목</p>
						<dl>
							<dt><label for="portfolio_subject">제목*</label><span id="pjt_tit">0/40자</span></dt>					
							<dd><input type="text" name="subject" title="일러스트레이션의 제목을 입력해 주세요" placeholder="일러스트레이션의 제목을 입력해 주세요" id="subject" class="input_text placeholder"></dd>
							<dt><label for="portfolio_dsc">소개*</label><span id="pjt_desc">0/800자</span></dt>
							<dd><textarea name="content" title="소개글을 입력해 주세요" placeholder="소개글을 입력해 주세요" id="content" cols="120" rows="9" class="textarea placeholder"></textarea></dd>
						</dl>
						<p class="check">
							<input type="checkbox" name="secret" id="secret">
							<label for="projectOpenYn">비공개</label>
						</p>
					</fieldset>
				</div>
			</div>
			<!-- //개요 -->
			
			<!-- 섬네일 이미지 -->
			<div class="section_thumbnail">
				<div class="tit">
	                <h4><span>02</span>썸네일 이미지</h4>
	                <p>목록에 보여질 썸네일 이미지를 선택해서<br>
	                	크롭 영역을 조절한 후<br>
						크롭 영역을 더블클릭해주세요.</p>
	            </div>
				<div class="form">
					<div class="cover">
						<!-- 미리보기 영역 -->
						<div id="img-preview"></div>
						<!-- 부트 스트랩 적용 input버튼 -->
						<!-- The fileinput-button span is used to style the file input field as button -->
						<span class="btn btn-success fileinput-button">
					        <i class="glyphicon glyphicon-plus"></i>
					        <span>이미지 선택</span>
					        <!-- The file input field used as target for the file upload widget -->
					        <input id="thumsnail_upFile" type="file" name="files[]" multiple/>
					    </span>
					    <!-- 업로드 프로그래스바 -->
						<div id="progress_thumbnail" class="progress">
							<div class="progress-bar progress-bar-success"></div>
						</div>
					    
						<p class="dsc">썸네일 이미지는 <em>592X444</em> 사이즈에 최적화 되어있습니다.</p>
					    
					</div>
					<!-- 크롭 플러그인 -->
					<div class="img-container">
						<img class="cropper" src="">
					</div>
					<!-- 크롭데이터 -->			
					<input type="hidden" id="data-x1">
					<input type="hidden" id="data-y1">
					<input type="hidden" id="data-height">
					<input type="hidden" id="data-width">
				</div>
			</div>
			<!-- //섬네일 업로드 -->

			
			<!-- 이미지 추가 -->
			<div class="image_upload">
				<div class="section_portfolio">
					<div class="tit">
						<h4><span>03</span>이미지</h4>
						<p>이미지를 선택하고<br>제목과 설명, 태그를 입력해 주세요.</p>
					</div>
						<div class="form">
							<form id="appendform">
							<input type="hidden" id="tempcount" name="tempcount">		
								<div id="fileButton" class="browsebutton">
								    <!-- 부트 스트랩 적용 input버튼 -->
									<!-- The fileinput-button span is used to style the file input field as button -->
									<span class="btn btn-success fileinput-button appendFile">
								        <i class="glyphicon glyphicon-plus"></i>
								        <span>이미지 추가</span>
								        <!-- The file input field used as target for the file upload widget -->
								        <input id="appendFile_upFile" type="file" name="files[]" multiple>
								    </span>
								    <!-- 업로드 프로그래스바 -->
									<div id="progress_append" class="progress">
								        <div class="progress-bar progress-bar-success"></div>
								    </div>
								</div>
							</form>
							<ul class="dsc">
					            <li>최적 가로 사이즈는 <em>896픽셀</em> 이하입니다.</li>
					            <li>파일 1개의 용량제한은 5MB이며 개수는 딱히 제한없이 업로드 할 수 있습니다.</li>
					            <li>이미지 파일은 jpg, png, gif만 가능합니다.</li>
				        	</ul>
						</div>
					</div>
					<div id="sortable" class="section_portfolio_add">
					    <!-- 이미지 추가 영역 -->						
					</div>
				</div>
				<!-- 버튼 -->
				<div class="btn_area">
				
				<a href="#" class="btn_ty5">업로드 완료</a>
				<p class="dsc">
					저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 이미지는 이용약관 및 관련 법률에 의하여 제재를 받으실 수 있습니다.
					<a href="/terms.grfl" target="_blank">이용약관보기</a>
				</p>
			</div>
		</div>
		</div>
		<script src="<%=path%>/js/upload.js"></script>
		<!-- Footer -->
		<jsp:include page="/include/footer.jsp" />
	</div>
</body>
</html>