package com.photofolio.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.photofolio.DTO.Article;
import com.photofolio.DTO.Member;
import com.photofolio.Util.ConnectionHelper;

public class MemberAticleListDAO {
	   public ArrayList<Article> memberlist(String id){
			ArrayList<Article> memberlist = new ArrayList<Article>();
		     
				String sql ="";
						
				
						
			
					sql="select * from article where id =?";
			
			    java.sql.Connection conn=null;
			    PreparedStatement pstmt = null;
			    ResultSet rs = null;
			    try{
			        Context init = new InitialContext();
			        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
			        conn = ds.getConnection();
			        pstmt =conn.prepareStatement(sql);
			        pstmt.setString(1,id);
					rs = pstmt.executeQuery();
					while(rs.next()){
						 Article artcle = new Article();
						artcle.setSubject(rs.getString("subject"));
						artcle.setContent(rs.getString("content"));
						artcle.setThumbnail(rs.getString("thumbnail"));
						artcle.setWritedate(rs.getDate("writedate"));
						artcle.setIdx(rs.getInt("idx"));
						memberlist.add(artcle);
						}
					
				
				   
					}catch(Exception e){
						System.out.println("DB연결 실패:" + e);
						 
					}finally{
						ConnectionHelper.close(pstmt);
						ConnectionHelper.close(conn);
					}
			  
				return memberlist;

				}
}
