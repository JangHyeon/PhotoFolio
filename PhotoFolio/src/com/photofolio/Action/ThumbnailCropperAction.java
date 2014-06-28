//기존에 작업했던 서비스 계층
//글을 쓰는 서비스 
//Action 패키지 안에 구현

package com.photofolio.Action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photofolio.Util.ThumbnailCreater;

public class ThumbnailCropperAction implements Action {
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
	    String savedir = "storage/thumbnail"; 	//저장 폴더명
	    int cropwidth = 592;			//크롭 넓이
	    int cropheight = 444;			//크롭 높이
	    
	    String pathname = root + File.separator + savedir;//즉, 루트경로 + \ (구분자) + 저장폴더;
	      
		int x = Integer.parseInt(request.getParameter("x"));
		int y = Integer.parseInt(request.getParameter("y"));
		int width = Integer.parseInt(request.getParameter("width"));
		int height = Integer.parseInt(request.getParameter("height"));
		String filename =request.getParameter("filename");

		String result="";
		if(width>=cropwidth){
		    System.out.println("path-"+pathname);
		    System.out.println("name-"+filename);
			//ThumbnailCreate.resize(pathname+ File.separator +FilesystemName, "Resized_", 500, 400);
			//ThumbnailCreate.crop(pathname+ File.separator +FilesystemName, "_croped", x, y, width,height);
			//ThumbnailCreate.crop(pathname+ File.separator +filename, x, y, width,height);
			result = ThumbnailCreater.cropAndResize(pathname+ File.separator +filename, x, y, width,height,cropwidth,cropheight);
			if(!result.equals("cropAndResize_success")){
				File df = new File(pathname+ File.separator +filename);
				if(df.exists()){
					boolean delFlag = df.delete();
					if(delFlag){
						System.out.println("처리 실패한 파일 삭제");
					}else{
						System.out.println("처리 실패한 파일 삭제 실패");
					}
				}
			}
		}
		
		// 한글 처리를 위한 response 설정
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-chche");
								 
		// 업로드 결과 전송
		PrintWriter outWriter = response.getWriter();
		outWriter.print(result);
		outWriter.flush();
		
		return null;
	}
}
