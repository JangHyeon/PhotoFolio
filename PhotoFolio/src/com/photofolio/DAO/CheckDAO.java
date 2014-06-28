package com.photofolio.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.photofolio.Util.ConnectionHelper;

public class CheckDAO {
	   
	   public int checkid(String id){
		 	
			
		    java.sql.Connection conn=null;
		    PreparedStatement pstmt = null;
		    ResultSet rs= null;
		    int reslut=0;
		    try{
		        Context init = new InitialContext();
		        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		        conn = ds.getConnection();
		        pstmt = conn.prepareStatement("select * from member where id=?");
		        pstmt.setString(1,id);
	          
		        rs=  pstmt.executeQuery();
		        
		        if(rs.next()){
		        	
		        	reslut = 1; 
		        }else{
		        	reslut =0;
		        }
					
				  
				}catch(Exception e){
					System.out.println("DB연결 실패:" + e);
					 
				}finally{
					ConnectionHelper.close(pstmt);
					ConnectionHelper.close(conn);
				}
		
				return reslut;
		   
			}
	   
	   public int checknickname(String nickname){
		 	
			
		    java.sql.Connection conn=null;
		    PreparedStatement pstmt = null;
		    ResultSet rs= null;
		    int reslut=0;
		    try{
		        Context init = new InitialContext();
		        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		        conn = ds.getConnection();
		        pstmt = conn.prepareStatement("select * from member where nickname=?");
		        pstmt.setString(1,nickname);
	          
		        rs=  pstmt.executeQuery();
		        
		        if(rs.next()){
		        	
		        	reslut = 1; 
		        }else{
		        	reslut =0;
		        }
					
				  
				}catch(Exception e){
					System.out.println("DB연결 실패:" + e);
					 
				}finally{
					ConnectionHelper.close(pstmt);
					ConnectionHelper.close(conn);
				}
		
				return reslut;
		   
			}
	
	
}
