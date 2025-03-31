package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShopDAO {
	public ShopMember searchUser(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ShopMember sm = null;
		
		
		try {
			String query = "SELECT * FROM SHOP_MEMBER WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(query);
			
			if (rset.next()) {
				String mId = rset.getString("MEMBER_ID");
				String memberPw = rset.getString("MEMBER_PW");
				String phone = rset.getString("PHONE");
				char gender = rset.getString("GENDER").charAt(0);
				
				System.out.printf("아이디 : %s / 패스워드 : %s / 전화번호 : %s / 성별 : %s / ", 
						memberId, memberPw, phone, gender
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			
			
			try {
				
				// 만들어진 역순으로 close 수행
				if(conn != null) conn.close();
				if(sm != null) sm.close();
				if(rset != null) rset.close();
				if(pstmt != null) pstmt.close();
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
		
	}

		}
	}
}

