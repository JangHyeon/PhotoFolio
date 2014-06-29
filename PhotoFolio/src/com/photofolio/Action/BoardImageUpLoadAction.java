package com.photofolio.Action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.photofolio.Util.ThumbnailCreater;

public class BoardImageUpLoadAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String root = request.getSession().getServletContext().getRealPath("/");//물리적 경로
	    
	    // File.separator - 폴더와 파일을 구분해서 쓰고자 할때-----------------------------------
	    /* 
	       자바 슬로건 : Write Once Run Anywhere
		   구분자를 사용
           Windows = %path;C:\java\jdk 와 같이 구분자가  ;(세미콜론) 파일 구분자 \(역슬래시) 사용
           Linux = 분할자 :(콜론) 파일구분자 /(슬래시) 사용
        */
	    String savedir = "storage/image"; //저장 폴더명
	    String thumbnailsavedir = "storage/image_thumbnail"; //저장 폴더명

	    String pathname = root + File.separator + savedir;//즉, 루트경로 + \ (구분자) + 저장폴더;
	    String thumbnailpathname = root + File.separator + thumbnailsavedir;
	      
	    File f = new File(pathname);
	    if(! f.exists()){ //폴더가 존재하지 않으면 폴더 작성
	        f.mkdirs();
	    }

	    File s = new File(thumbnailpathname);
	    if(! s.exists()){ //폴더가 존재하지 않으면 폴더 작성
	        s.mkdirs();
	    }
	    
	    String encType = "UTF-8";
	    int maxFilesize = 5*1024*1024; //5MB
	      
	    //MultipartRequest(request, 파일을 저장할 경로, 문자셋, 동일한 파일명 보호)
	    MultipartRequest mr = new MultipartRequest(
	    	request, //jsp가 가지는 request(요청객체) 얻어오기
	    	pathname, //저장 경로
	    	maxFilesize, //한번에 업로드 할 수 있는 크기
	    	encType, //인코딩 타입
	    	//new DefaultFileRenamePolicy() //파일명 중복처리 객체 사용
	    	new FileRenamePolicy() { //인터페이스 구현
				@Override
				public File rename(File f) {
					String name = f.getName();
					//////////////////
					//날자를 기준으로 파일명 재설정
				    //String body = new SimpleDateFormat("yyyyMMddHmsS").format(new Date()); //현재시간
					//String body = SupinanDateFormat.getInstance("yyyyMMddhmsS").format(new Date()); //SupinanDateFormat
					
					///////////////////////////////
					//UUID 파일명 재설정
					String body = UUID.randomUUID().toString().replace("-", ""); //UUID에있는 -(하이픈) 제거
				    String ext = null;
				    
				    int dot = name.lastIndexOf(".");
				    if (dot != -1) {
				    	ext = name.substring(dot);  // includes "."   .포함 확장자
				    }
				    else {      
				      ext = "";
				    }
				    String newName = body + ext.toLowerCase();
				    f = new File(f.getParent(), newName);
				    
				    return f;
				}
			}
	    );
	    
	    
//--------------------------------업로드 끝-----------------------------------------------------------

	    //request 객체를 MultipartRequest 대신해서 처리
	    //System.out.println(request.getParameter("title"));  // console -> null
	    //System.out.println(mr.getParameter("title"));  // console -> "제목을 입력하세요"

	    int childrenNum = Integer.parseInt(mr.getParameter("tempcount"));
	    
	    
		@SuppressWarnings("unchecked")
		Enumeration<String> filenames = mr.getFileNames();

		String file = (String)filenames.nextElement();
		String FilesystemName = mr.getFilesystemName(file);
		String OriginalFileName = mr.getOriginalFileName(file);
		long filesize = mr.getFile(file).length();
		
		System.out.println("---upload 완료---");
		System.out.println("real file name = " + FilesystemName);
		System.out.println("ori file name = " + OriginalFileName);
		System.out.println("file size = " + filesize);
		
		
		
		//------------------Image 후처리----------
		if(!(mr.getParameter("tempcount")==null) || !mr.getParameter("tempcount").trim().equals("")){
			String result = ThumbnailCreater.thumbnailcropAndResize(pathname+File.separator+FilesystemName, thumbnailpathname+File.separator+FilesystemName);
			System.out.println("result---------"+result);
			if(!result.equals("thumbmailcreate_success")){
				File df = new File(pathname+ File.separator +FilesystemName);
				if(df.exists()){
					boolean delFlag = df.delete();
					if(delFlag){
						System.out.println("처리 실패한 파일 삭제");
					}else{
						System.out.println("처리 실패한 파일 삭제 실패");
					}
				}
				if(result.equals("Dimensions do not match")){
					result = "alert('크롭이미지를 생성할 수 없습니다.\n크롭영역을 조정하시고 다시 시도해주세요.')";
    			}else if(result.equals("Unsupported format or not found")){
    				result = "alert('파일을 찾을 수 없거나 지원하는 포맷이 아닙니다.\n다시 시도해주세요.')";
    			}
			}else{
				// 한글 처리를 위한 response 설정
				response.setContentType("text/plain;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Cache-Control", "no-chche");
							 
				// 업로드 결과 전송
				PrintWriter outWriter = response.getWriter();
											
				result ="<div id=\"addItem"+childrenNum+"\" class=\"add_img sort-item temp-item\">";
				result +="<fieldset>";
				result +="<legend>이미지 추가</legend>";
				result +="<dl>";
				result +="<dt><label for=\"img_subject\">제목</label></dt>";
				result +="<dd><input type=\"text\" name=\"img_subject\" maxlength=\"20\" placeholder=\"이미지 제목, 제작년도, 사용기법\" id=\"img_subject"+childrenNum+"\" class=\"input_text placeholder temp-tit\"></dd>";
				result +="<dt><label for=\"img_dsc"+childrenNum+"\">설명</label></dt>";
				result +="<dd><textarea name=\"img_dsc\" placeholder=\"이미지에 대한 간단한 설명을 써주세요.\" id=\"img_dsc"+childrenNum+"\" cols=\"50\" rows=\"4\" maxlength=\"400\" class=\"textarea placeholder temp-dsc\"></textarea></dd>";
				result +="<dt class=\"thumb\">이미지 썸네일</dt>";
				result +="<dd class=\"thumb\">";
				result +="<div id=\"imageView"+childrenNum+"\">";
			    result +="<img id=\"imageView"+childrenNum+"\" src=\"../storage/image_thumbnail/"+FilesystemName+"\" onerror=\"this.src='../images/no144_108.jpg\'\">";
			    result +="<input type=\"hidden\" name=\"ori_file_name\" id=\"ori_file_name"+childrenNum+"\" value=\""+OriginalFileName+"\">";
			    result +="<input type=\"hidden\" name=\"file_name\" id=\"file_name"+childrenNum+"\" value=\""+FilesystemName+"\">";
			    result +="<input type=\"hidden\" name=\"file_size\" id=\"file_size"+childrenNum+"\" value=\""+filesize+"\">";
			    result +="</div>";
			    result +="</dd>";
			    result +="<dt><label for=\"img_tag\">태그*</label></dt>";
			    result +="<dd><input type=\"text\" name=\"img_tag\" maxlength=\"40\" placeholder=\"태그는 쉼표(,) 로 구분됩니다.\" id=\"img_tag"+childrenNum+"\" class=\"input_text placeholder temp-tag\"></dd>";
			    result +="</dl>";
			    result +="<span class=\"btn_move handle\"></span>";
			    result +="<a href=\"#\" class=\"btn_close\">닫기</a>";
			    result +="</fieldset>";
			    result +="<span class=\"cmt\"></span>";
			    result +="</div>";
								
				outWriter.print(result);
				outWriter.flush();
				//System.out.println(pathname+ File.separator +FilesystemName);
			}
		}
		return null;
	}
}
