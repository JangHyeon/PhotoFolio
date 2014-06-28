package com.photofolio.DAO;

import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.photofolio.DTO.Member;
import com.photofolio.Util.ConnectionHelper;

public class EditDAO {

	public int editMember(Member memberdto){
		 int rs=0;
	   String sql = "update member set nickname =?,email =?,phone =?,address =?,profileimg =?,memo =? where id=?";
		
		
	    java.sql.Connection conn=null;
	    PreparedStatement pstmt = null;
	    try{
	        Context init = new InitialContext();
	        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
	        conn = ds.getConnection();
	        pstmt = conn.prepareStatement(sql);
	       
       
            pstmt.setString(1,memberdto.getNickname());
            pstmt.setString(2,memberdto.getEmail());
            pstmt.setString(3, memberdto.getPhone());
            pstmt.setString(4, memberdto.getAddress());
            pstmt.setString(5, memberdto.getProfileimg());
            pstmt.setString(6, memberdto.getMemo());
            pstmt.setString(7,memberdto.getId());
    	    rs=  pstmt.executeUpdate();
            
            if(memberdto.getLevel()>0){
           	 pstmt = conn.prepareStatement("update creator set homepage=?,cmemo=?,history=? where id=?");
                pstmt.setString(1,memberdto.getHomepage());
                pstmt.setString(2,memberdto.getCmemo());
                pstmt.setString(3, memberdto.getHistory());
           	pstmt.setString(4, memberdto.getId());
    	    rs=  pstmt.executeUpdate();
           }
            
		
				
			    
			}catch(Exception e){
				System.out.println("DB연결 실패:" + e);
				 
			}finally{
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			}
	
			return rs;
	}
}
