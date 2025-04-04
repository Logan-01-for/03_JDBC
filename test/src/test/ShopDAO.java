package test;

import static edu.kh.jdbc.common.JDBCTemplate.close;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShopDAO {
	public int inserUser(Connection conn, ShopMember sm) {
		 PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			String query = "INSERT INTO SHOP_MEMBER VALUES(?, ?,,?, ?)";
			pstmt = conn.prepareStatement(query);
	          pstmt.setString(1, sm.getMemberId());
	          pstmt.setString(2, sm.getMemberPw());
	          pstmt.setString(3, sm.getPhone());
	          pstmt.setString(4, sm.getGender()+"");
	          result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		
	}finally {
	
		close(pstmt);
			
	}
		return result;
	}
        

	}

	