package com.photofolio.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.photofolio.DTO.Member;
import com.photofolio.Util.ConnectionHelper;

public  class IdchkDAO{
	
	public int IdchkDAO(String id){
		
		System.out.println("중복체크크으으크크크크킄"+id);

	   String sql = "select id from member where id=?";
		
		ResultSet rs = null;
		int result = 0;
	    java.sql.Connection conn=null;
	    PreparedStatement pstmt = null;
	    
	    try{
	        Context init = new InitialContext();
	        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
	        conn = ds.getConnection();
	        pstmt = conn.prepareStatement(sql);

	        pstmt.setString(1, id);

			    rs=  pstmt.executeQuery();
			    
			    if(rs.next()){
			    	
			    	result=1;
			    }else{
			    	
			    	result=0;
			    }				
			  
			}catch(Exception e){
				System.out.println("DB연결 실패:" + e);
				 
			}finally{
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			}
	
	   return result;
	}
	
	
public int NickDAO(String nickname){
		
		System.out.println("닉 네 임 중복체크크으으크크크크킄"+nickname);

	   String sql = "select id from member where nickname=?";
		
		ResultSet rs = null;
		int result = 0;
	    java.sql.Connection conn=null;
	    PreparedStatement pstmt = null;
	    
	    try{
	        Context init = new InitialContext();
	        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
	        conn = ds.getConnection();
	        pstmt = conn.prepareStatement(sql);

	        pstmt.setString(1, nickname);

			    rs=  pstmt.executeQuery();
			    
			    if(rs.next()){
			    	
			    	result=1;
			    }else{
			    	
			    	result=0;
			    }				
			  
			}catch(Exception e){
				System.out.println("DB연결 실패:" + e);
				 
			}finally{
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			}
	
	   return result;
	}
}
