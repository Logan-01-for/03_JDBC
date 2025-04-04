package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShopDAO1 {
    public int updateUser(Connection conn, ShopMember sm) {
        PreparedStatement pstmt = null;
        int result = 0;
        
        try {
            
            String sql = "UPDATE SHOP_MEMBER SET PHONE = ? WHERE MEMBER_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sm.getPhone());  
            pstmt.setString(2, sm.getMemberId());  
            result = pstmt.executeUpdate();  
            
        } catch (SQLException e) {
            e.printStackTrace();  
            
        } finally {
            close(pstmt);  
        }
        return result;
    }

}

       
             