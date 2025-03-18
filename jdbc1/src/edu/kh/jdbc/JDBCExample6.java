package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample6 {
    public static void main(String[] args) {
        // 아이디, 비밀번호, 이름을 입력받아
        // 아이디, 비밀번호가 일치하는 사용자의
        // 이름을 수정(UPDATE)
        // 1. PreparedStatement 이용하기
        // 2. commit/rollback 처리하기
        // 3. 성공 시 "수정 성공!" 출력 / 실패 시 "아이디 또는 비밀번호 불일치" 출력

    	// 1) JDBC 객체 참조 변수 선언 + 키보드 입력용 객체 SC 선언
        Connection conn = null;
        PreparedStatement pstmt = null;
        Scanner sc = null;

        try {
            // Oracle JDBC Driver 로딩
        	// 2) Connection 객체 생성 (DriverManager 를 통해서)
        	// 2-1) OracleDriver 메모리에 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2-2) DB  연결정보 작성
            String url = "jdbc:oracle:thin:@localhost:1521:XE"; // 드라이버의 종류
            String userName = "kh";
            String password = "kh1234";

            // DB 연결
            conn = DriverManager.getConnection(url, userName, password);

            // 3. SQL 작성 + AutoCommit 끄기
            conn.setAutoCommit(false);
            
            // Scanner 객체 생성 (입력 받기 위한)
            sc = new Scanner(System.in);

            System.out.print("아이디 입력 : ");
            String id = sc.nextLine();

            System.out.print("비밀번호 입력 : ");
            String pw = sc.nextLine();

            System.out.print("수정할 이름 입력 : ");
            String name = sc.nextLine();

            // 수정 쿼리, 테스트 블럭
            String sql = """
                    UPDATE TB_USER
                    SET USER_Name = ?
                    WHERE USER_Id = ? 
                    AND USER_PW = ?
                    """;

            // 4. PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(sql);
            
            // 5. ? 에 알맞은 값 세팅
            pstmt.setString(1, name);   // 이름
            pstmt.setString(2, id);        // 아이디
            pstmt.setString(3, pw);  // 비밀번호

            // 쿼리 실행
            // 6. SQL 수행 후 결과 값 반환 받기
            // executeqUERY() : SELECT 수행후 ResultSet 반환
            // executeUpdate() : DML 수행 후 결과 행의 갯수 반환(int)
            int result = pstmt.executeUpdate();
           

            // 7. result 값에 따리 결과 처리 + commit rollback
            if (result > 0) {
                System.out.println("수정 성공!");
                conn.commit();  // 성공 시 commit
            } else {
                System.out.println("아이디 또는 비밀번호 불일치");
                conn.rollback();  // 실패 시 rollback
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	// 8. 사용 완료한 JDBC 객체 자원 반환
            try {
                // 자원 반납
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (sc != null) sc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
