package com.photofolio.DAO;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.photofolio.Util.ConnectionHelper;

public class DeleteDAO {
   
   public int Delete(String id,int lvl){
      
      int rs = 0;
      java.sql.Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         
         Context init = new InitialContext();
         DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
         conn = ds.getConnection();


        pstmt = conn.prepareStatement("delete from member where id=?");    
        pstmt.setString(1,id);
        int rs1 = pstmt.executeUpdate();
    
          if(rs1==0){
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