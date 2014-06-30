package com.photofolio.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



import com.photofolio.DTO.Member;
import com.photofolio.Util.ConnectionHelper;

public class ModifyDAO {
	   public Member modifymember(String id,int lvl){
			
	  	  Member memberdto = new Member();
		 
			String sql ="";
					
			
					
			if(lvl>0){		
				sql = "SELECT member.id,pwd,lvl,nickname,COALESCE(email,'email을입력하지않았습니다') as email,COALESCE(phone,'전화번호를입력하지않았습니다') as phone,COALESCE(address,'주소를 등록하지 않았습니다.') as address,profileimg,COALESCE(memo,'프로필정보를입력하지않았습니다') as memo,COALESCE(homepage,'홈페이지를 입력해주세요') as homepage,COALESCE(cmemo,'크리에이터 프로필을 입력해주세요')as cmemo,COALESCE(history,'history를입력해주세요')as history  FROM member INNER JOIN creator ON member.id = creator.id where member.id = ?";
			}else{
				sql="select id, pwd, lvl, nickname,email,phone,COALESCE(address,'주소를 등록하지 않았습니다.')as address,profileimg,COALESCE(memo,'자기소개가 없습니다.')as memo from member where id=?";
			}
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
					memberdto.setNickname(rs.getString("nickname"));
					memberdto.setPhone(rs.getString("phone"));
					memberdto.setEmail(rs.getString("email"));
					memberdto.setProfileimg(rs.getString("profileimg"));
					memberdto.setAddress(rs.getString("address"));
					memberdto.setMemo(rs.getString("memo"));
					if(lvl>0){
					memberdto.setHomepage(rs.getString("homepage"));
					memberdto.setCmemo(rs.getString("cmemo"));
					memberdto.setHistory(rs.getString("history"));
					}
				}
			
			   
				}catch(Exception e){
					System.out.println("DB연결 실패:" + e);
					 
				}finally{
					ConnectionHelper.close(pstmt);
					ConnectionHelper.close(conn);
				}
		  
			return memberdto;

			}
	   public ArrayList<Member> modifymember(String id){
			
		   		String sql="select * from member where id=?";
	
			    java.sql.Connection conn=null;
			    PreparedStatement pstmt = null;
			    ResultSet rs = null;
			    ArrayList<Member> info = new ArrayList<Member>();
			    Member member = new Member();
			    try{
			        Context init = new InitialContext();
			        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
			        conn = ds.getConnection();
			        pstmt =conn.prepareStatement(sql);
			        pstmt.setString(1,id);
					rs = pstmt.executeQuery();
					if(rs.next()){
						member.setId(rs.getString("id"));
						member.setProfileimg(rs.getString("profileimg"));
						member.setNickname(rs.getString("nickname"));
						member.setEmail(rs.getString("email"));
						member.setAddress(rs.getString("address"));
						member.setMemo(rs.getString("memo"));
						info.add(member);
						}
					}catch(Exception e){
						System.out.println("DB연결 실패:" + e);
						 
					}finally{
						ConnectionHelper.close(pstmt);
						ConnectionHelper.close(conn);
					}
			  
				return info;

				}
		
	
}
