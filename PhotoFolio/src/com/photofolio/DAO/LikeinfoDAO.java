package com.photofolio.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.catalina.connector.Request;

import com.photofolio.DTO.Member;
import com.photofolio.Util.ConnectionHelper;

public class LikeinfoDAO {
	public ArrayList<Member> info(String id){
		ArrayList<String> list = new ArrayList<String>();
		String sql ="";
				
		ArrayList<Member> memberinfo = new ArrayList<Member>();
			
	    sql = "select interest from member join interest on member.id = interest.id where member.id=?";
		
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
			list.add(rs.getString("interest"));
			}
			for(int i=0;i<list.size();i++){
			System.out.print("아이디"+list.get(i));
			pstmt =conn.prepareStatement("select*from member where id=?");
	        pstmt.setString(1,list.get(i));
			rs = pstmt.executeQuery();
			if(rs.next()){
				Member dto = new Member();
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setMemo(rs.getString("memo"));
				dto.setProfileimg(rs.getString("profileimg"));
				memberinfo.add(dto);
			}


			
			}
			
		
			}catch(Exception e){
				System.out.println("DB연결 실패:" + e);
				 
			}finally{
				
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
				
			}
	 
	    return memberinfo;
		
		

		}
	public ArrayList<Member> info2(String id){
		ArrayList<String> list = new ArrayList<String>();
		String sql ="";
				
		ArrayList<Member> memberinfo = new ArrayList<Member>();
			
	    sql = "select*from interest where interest=?;";
		
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
			list.add(rs.getString("id"));
			}
			for(int i=0;i<list.size();i++){
			System.out.print("아이디"+list.get(i));
			pstmt =conn.prepareStatement("select*from member where id=?");
	        pstmt.setString(1,list.get(i));
			rs = pstmt.executeQuery();
			if(rs.next()){
				Member dto = new Member();
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setMemo(rs.getString("memo"));
				dto.setProfileimg(rs.getString("profileimg"));
				memberinfo.add(dto);
			}


			
			}
			
		
			}catch(Exception e){
				System.out.println("DB연결 실패:" + e);
				 
			}finally{
				
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
				
			}
	 
	    return memberinfo;
		

			}

}
