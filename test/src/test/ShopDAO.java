package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShopDAO {

    // 회원 ID로 사용자를 검색하는 메서드
    public ShopMember searchUser(Connection conn, String memberId) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ShopMember sm = null;
        
        // 올바른 순서: 먼저 SQL 쿼리를 작성한 후 PreparedStatement를 준비
        String query = "SELECT * FROM SHOP_MEMBER WHERE MEMBER_ID = ?";
        
        try {
            // PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(query);
            
            // 쿼리의 위치 홀더에 값 설정
            pstmt.setString(1, memberId);
            
            // 쿼리 실행 후 결과 받기
            rset = pstmt.executeQuery();
            
            // 결과가 있다면
            if (rset.next()) {
                // 결과에서 데이터를 가져오기
                String mId = rset.getString("MEMBER_ID");
                String memberPw = rset.getString("MEMBER_PW");
                String phone = rset.getString("PHONE");
                char gender = rset.getString("GENDER").charAt(0);
                
                // ShopMember 객체 생성 및 데이터 설정
                sm = new ShopMember(mId, memberPw, phone, gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 자원 반납: 생성된 역순으로 close
            try {
                if (rset != null) rset.close();
                if (pstmt != null) pstmt.close();
                // conn.close()는 여기서 닫지 않음. 외부에서 관리하는 경우라면 닫지 말아야 합니다.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // 찾은 ShopMember 객체 반환 (찾지 못하면 null 반환)
        return sm;
    }
}
