package com.photofolio.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import com.photofolio.Action.ActionForward;
import com.photofolio.Util.ConnectionHelper;

public class LikeAddDAO {
	public int info(String id,String listid) {
		int rs = 0;
		
		java.sql.Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		String sql1 = "select * from interest where id=? and interest=?";
	
		String sql = "insert into interest(id, interest) values(?, ?);";
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
			conn = ds.getConnection();


			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			pstmt.setString(2, listid);
			result=pstmt.executeQuery();
			
			System.out.println("likeadd id : "+ id+ "likeadd listid : "+ listid);
			
			if(result.next()){
		    	System.out.println("추가 실패");
		    	rs = 0;

			}else{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, listid);
			    rs=  pstmt.executeUpdate();
			    System.out.println("추가 성공");
			    rs=1;

			     
			}
		
			
		    
	
		} catch (Exception e) {
			System.out.println("DB연결 실패:" + e);
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return rs;
	
	}

}
