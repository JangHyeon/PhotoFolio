package com.photofolio.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.catalina.connector.Request;

import com.photofolio.DTO.Member;
import com.photofolio.Util.ConnectionHelper;

public class LoginDAO {
	   public int loginmember(Member memberdto){
		
	  		int result = 0; 

		 
			String sql = "select pwd,nickname,lvl,profileimg from member where id=?";
			
			
		    java.sql.Connection conn=null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    try{
		        Context init = new InitialContext();
		        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
		        conn = ds.getConnection();
			    pstmt =conn.prepareStatement(sql);
				pstmt.setString(1,memberdto.getId());
					
				rs = pstmt.executeQuery();
				System.out.println(memberdto.getId());
				if(rs.next()){
					if(memberdto.getPwd().equals(rs.getString("pwd"))){
						memberdto.setNickname(rs.getString("nickname"));
						memberdto.setLevel(Integer.parseInt(rs.getString("lvl")));
						if(rs.getString("profileimg")!=null){memberdto.setProfileimg(rs.getString("profileimg"));};
						result = 1;
					}else{
						result = 0;
					}
					}else{
						System.out.println("불일치");	
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

