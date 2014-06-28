package com.photofolio.DAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.photofolio.Util.ConnectionHelper;

public class DeleteDAO {
   
   public int Delete(String id,int lvl){
      
      int rs = 0;
      String sql = "";
    
    
      java.sql.Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         
         Context init = new InitialContext();
         DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
         conn = ds.getConnection();
         
         
             System.out.println("관심 삭제1");

             sql="delete from interest where id=?";
        	  pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();

            System.out.println("관심받은 삭제2");

            sql="delete from interest where interest=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate(); 
         
         if(lvl >0){
             System.out.println("크리에이터삭제3");

                sql="delete from creator where id =?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.executeUpdate();

         }
         
         System.out.println("일반회원 삭제4");
     	  sql="delete from member where id=?";
     	  pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
        
         
         
        int result = pstmt.executeUpdate();

         System.out.println("result2 : "+result);
          if(result==0){
             System.out.println("삭제 실패");
             rs = 0;
          }else{
             System.out.println("삭제 성공");
             rs = 1;
          }
        
          
          
         } catch (Exception e) {
            e.printStackTrace();
         }finally{
            ConnectionHelper.close(conn);
            ConnectionHelper.close(pstmt);
         }
         return rs;
}
      
}