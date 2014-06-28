package com.photofolio.DAO;

import java.sql.PreparedStatement;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.Connection;
import com.photofolio.DTO.Member;
import com.photofolio.Util.ConnectionHelper;

public class JoinDAO {
	int  rs ;
	   public int joinmember(Member memberdto){
		
		 
			
	
		   String sql = "insert into member(id,pwd,nickname,email,phone,address,profileimg,memo) values(?,?,?,?,?,?,?,?)";
			
			
		    java.sql.Connection conn=null;
		    PreparedStatement pstmt = null;
		    try{
		        Context init = new InitialContext();
		        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		        conn = ds.getConnection();
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1,memberdto.getId());
	               pstmt.setString(2,memberdto.getPwd());
	               pstmt.setString(3,memberdto.getNickname());
	               pstmt.setString(4,memberdto.getEmail());
	               pstmt.setString(5, memberdto.getPhone());
	               pstmt.setString(6, memberdto.getAddress());
	               pstmt.setString(7, memberdto.getProfileimg());
	               pstmt.setString(8, memberdto.getMemo());
	           
				    rs=  pstmt.executeUpdate();
					
				  
				}catch(Exception e){
					System.out.println("DB연결 실패:" + e);
					 
				}finally{
					ConnectionHelper.close(pstmt);
					ConnectionHelper.close(conn);
				}
		
				return rs;
		   
			}
	
	

		 
	   }

