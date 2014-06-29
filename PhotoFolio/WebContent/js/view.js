$(function() {	
	//게시물 삭제
	$('#projectDeleteForm').on('click',function(e){
		e.preventDefault();
		$.post("../boardorder/articledelete", {
			idx : $('#idx').val()
		}, function(returndata) {
			if (returndata == 0) {
				alert("삭제에 실패했습니다\n다시 시도해 주세요");
			}else{
				alert("삭제되었습니다.");
				location.href="../Sample.jsp";
			}
		}).fail(function() {
			alert("서버와 통신 중 문제가 발생했습니다");
		});
	});
	
	
	// 게시물 신고
	$('#openReportProjectButton').on(
		'click',
		function(e) {
			e.preventDefault();
			if($('#id').val()==""){
				e.preventDefault();
				if(confirm('로그인 후 이용해 주세요')==true){
					location.href="../member/login.jsp";
				}
			}else{
				if(confirm('이 댓글을 신고 하시겠습니까?')==true){
					$.post(
						"../boardorder/articlereport",
						{
							idx : $('#idx').val()
						},
						function(returndata) {
							if (returndata == 0) {
								alert("댓글 신고에 실패했습니다\n다시 시도해 주세요");
							} else {
								var report = $('#articlereport em');
								report.parent().css('display','block');
								var reportcount = report.text();
								report.text(reportcount * 1 + 1);
							}
						}
					).fail(function() {
						alert("서버와 통신 중 문제가 발생했습니다");
					});
				}
			}
		}
	);


	//로그인 채크
	$('.cmt_wrap').on('click',function(e){
		if($('#id').val()==""){
			e.preventDefault();
			if(confirm('로그인 후 이용해 주세요')==true){
				location.href="../member/login.jsp";
			}
		}
	});
	
	// 댓글 등록
	$('#registCommentButton').on('click',function(e){
		e.preventDefault();
		if($('#commentContent').val()==""){
			alert('댓글내용을 입력해주세요');
			$('#commentContent').focus();
			return;
		}
		$.post("../boardorder/replywrite", {
			idx : $('#idx').val(),
			id : $('#id').val(),
			nickname : $('#nickname').val(),
			content : $('#commentContent').val(),
			profileimg : $('#profileimg').val()
		}, function(returndata) {
			if (returndata == 0) {
				alert("댓글 등록에 실패했습니다\n다시 시도해 주세요");
			} else {// 성공시 댓글 동적 생성
				var reply = $('#commentCountEl');
				var replycount = reply.text()*1+1;
				reply.text(replycount);
				
				$('#commentContent').val("");
				$('#commentList').prepend(returndata);

			}
		}).fail(function() {
			alert("서버와 통신 중 문제가 발생했습니다");
		});
	});
	
	
    //글자수 제한
    var textLengthCheck = function(maxLength,count,input){
        var $count = $(count);
        var $input = $(input);
        
        $input.on('focus blur keydown keyup paste', function(){
        	var textLength = $input.val().length;
            var remain = maxLength - textLength;
            if (remain < 0) {    
            	var str = $input.val();
            	
            	alert('최대'+maxLength+"자까지 작성할 수 있습니다.");
            	$input.val(str.substr(0, maxLength));            
            	count = 0;       
            }
        	$count.text(textLength+"/"+maxLength+"자");  
        });
    };

    textLengthCheck(1000,'#comment_tit','#commentContent');
	

	// 댓글 삭제
	$('#commentList').on('click', '.del', function(e) {
		e.preventDefault();

		var del = $(this).parent().parent();
		var index = $('li').index(del);
		var reply_idx = del.attr('id').substring(8);

		$.post("../boardorder/replydelete", 
			{
				reply_idx : reply_idx
			}, 
			function(returndata) {
			if (returndata == 0) {
				alert("댓글 삭제에 실패했습니다\n다시 시도해 주세요");
			} else {
				var reply = $('#commentCountEl');
				var replycount = reply.text()*1-1;
				reply.text(replycount);
				
				$('#commentList').children('li').eq(index).remove();
			}
		}).fail(function() {
			alert("서버와 통신 중 문제가 발생했습니다");
		});
	});

	// 댓글 신고
	$('#commentList').on(
		'click',
		'.btn_report',
		function(e) {
			e.preventDefault();
			if($('#id').val()==""){
				e.preventDefault();
				if(confirm('로그인 후 이용해 주세요')==true){
					location.href="../member/login.jsp";
				}
			}else{
				if(confirm('이 댓글을 신고 하시겠습니까?')==true){
					var del = $(this).parent().parent().parent();
					var index = $('li').index(del);
					var reply_idx = del.attr('id').substring(8);
					$.post(
						"../boardorder/replyreport",
						{
							reply_idx : reply_idx
						},
						function(returndata) {
							if (returndata == 0) {
								alert("댓글 신고에 실패했습니다\n다시 시도해 주세요");
							} else {
								var report = $('#commentList').children('li').eq(index).find('strong');
								report.css('display','inline');
								var reportcount = report.text();
								report.text(reportcount * 1 + 1);
							}
						}
					).fail(function() {
						alert("서버와 통신 중 문제가 발생했습니다");
					});
				}
			}
		}
	);
	
	
	
	// 게시물 좋아요
	$('#likeButton').on(
		'click',
		function(e) {
			e.preventDefault();
			if($('#id').val()==""){
				e.preventDefault();
				if(confirm('로그인 후 이용해 주세요')==true){
					location.href="../member/login.jsp";
				}
			}else if($('#nickname').val()==$('.name a').text()){
				$('#fadeAlertMyProject').css('display','block').delay(800).fadeOut(1000);
				return;
			}else{
				$.post(
					"../boardorder/articlelike",
					{
						idx : $('#idx').val()
					},
					function(returndata) {
						if (returndata == 0) {
							alert("좋아요가 실패했습니다\n다시 시도해 주세요");
						} else {
							var like = $('#likeCount');
							var likecount = like.text()*1+1;
							like.text(likecount);
							$('#recommendCountEl').text(likecount);
							
							$('.ic').children('img').remove();
							$('<img>').attr({
								   src:'http://img.grafolio.kr/pc/ic_like.png',
								   class:'heart'
							}).prependTo('.ic').css({
								width: '5px',
							    height: '5px',
							    opacity: '0.3'
							}).animate({ 
							    width: '34px',
							    height: '30px',
							    opacity: '1'
							}, {
							    queue: false, 
							    duration: 1000, 
							    easing: 'easeOutElastic',
							    complete: function() {
							        
							    }
							});
							//.fadeIn(1000, function(){}); //자식으로 추가되어 안에 img src태그가 추가됨
						}
					}
				).fail(function() {
					alert("서버와 통신 중 문제가 발생했습니다");
				});
			}
			
		}
	);

});