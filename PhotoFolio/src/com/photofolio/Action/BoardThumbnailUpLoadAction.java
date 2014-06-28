package com.photofolio.Action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class BoardThumbnailUpLoadAction implements Action {
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
	    String savedir = "storage/thumbnail"; //저장 폴더명
	    
	    String pathname = root + File.separator + savedir;//즉, 루트경로 + \ (구분자) + 저장폴더;
	      
	    File f = new File(pathname);
	    if(! f.exists()){ //폴더가 존재하지 않으면 폴더 작성
	        f.mkdirs();
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
				    
				    
				    
				    String newName = body + ext;
				    f = new File(f.getParent(), newName);
				    
				    return f;
				}
			}
	    );
	    
	    
//--------------------------------업로드 끝-----------------------------------------------------------

	    //request 객체를 MultipartRequest 대신해서 처리
	    //System.out.println(request.getParameter("title"));  // console -> null
	    //System.out.println(mr.getParameter("title"));  // console -> "제목을 입력하세요"
	    
		@SuppressWarnings("unchecked")
		Enumeration<String> filenames = mr.getFileNames();

		String file = (String)filenames.nextElement();
		String FilesystemName = mr.getFilesystemName(file);
		String OriginalFileName = mr.getOriginalFileName(file);
		long filesize = mr.getFile(file).length();
		
		System.out.println("---upload 완료---");
		System.out.println("file = " + file);
		System.out.println("real file name = " + FilesystemName);
		System.out.println("ori file name = " + OriginalFileName);
		System.out.println("file size = " + filesize);
	
				
		//------------------Image 후처리----------
		if(mr.getParameter("x")==null || mr.getParameter("x").trim().equals("")){
			// 업로드 파일 정보 셋팅
			Map<String, Object> mapResult = new HashMap<String, Object>();
			//mapResult.put("ori_file_name" , OriginalFileName);
			mapResult.put("file_name" , FilesystemName);
			mapResult.put("file_path" , request.getContextPath()+"/"+savedir+"/");
			//mapResult.put("file_size" , filesize);
						   
			// 한글 처리를 위한 response 설정
			response.setContentType("text/plain;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-chche");
						 
			// 업로드 결과 전송
			JSONObject json = new JSONObject(mapResult);
			//System.out.println(mapResult.toString());
			PrintWriter outWriter = response.getWriter();
			outWriter.print(json.toString());
			outWriter.flush();
			//System.out.println(pathname+ File.separator +FilesystemName);
			
		}
		return null;
	}
}
