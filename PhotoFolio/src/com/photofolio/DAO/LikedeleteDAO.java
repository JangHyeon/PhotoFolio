package com.photofolio.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.photofolio.Util.ConnectionHelper;

public class LikedeleteDAO {
  public int likedelete(String id,String myid){
	  
	  
	  
	  
	  
	  			int  result = 0;
			    java.sql.Connection conn=null;
			    PreparedStatement pstmt = null;
			    ResultSet rs = null;
			    try{
			        Context init = new InitialContext();
			        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
			        conn = ds.getConnection();
				    pstmt =conn.prepareStatement("select * from interest where id=? and interest =?");
					pstmt.setString(1,myid);
					pstmt.setString(2,id);	
					rs = pstmt.executeQuery();
					if(rs.next()){
						
					       pstmt =conn.prepareStatement("delete  from interest where id=? and interest=?");
					       pstmt.setString(1,myid);
					       pstmt.setString(2,id);	
						  pstmt.executeUpdate();
						result = 1;
					}else{
						result = 0;
					
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
