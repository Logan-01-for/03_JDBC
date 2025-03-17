package edu.kh.jdbc;
		import java.sql.Connection;
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
				
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				
				Scanner sc = null;
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url = "jdbc:oracle:thin:@localhost:1521:XE"; // 드리이버의 종류
					String userName = "kh";
					String password = "kh1234";
					
					conn
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			
			
			}
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		Scanner sc = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE"; // 드리이버의 종류
			String userName = "kh";
			String password = "kh1234";
			
			conn = DriverManager.getConnction(url, userName, password);
			
			sc = new Scanner(System.in);
			
			System.out.println("아이디 입력 : ");
			String id = sc.nextLine();
			
			System.out.println("비밀번호 입력 : ");
			String inputPassword = sc.nextLine();
			
			System.out.println("수정할 이름 입력 : ");
			String newName = sc.nextLine();
			
			String sql = """
					INSERT INTO TB_USER
					VALUES(SEQ_USER_NO.NEXTVAL, ?, ? ?, DEFAULT)
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newName);
			pstmt.setSting(2, id);
			pstmt.setString(3, inputPassword);
			
			conn.setAutoCommit(false);
			
			int result = pstmt.executeUpdate();
			
			if(result >0) {
				conn.commit();
				System.out.println("수정 성공!");
			}else {
				conn.rollback();
				System.out.println("추가 실패");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			
			try {
				
			if (pstmt !=null)pstmt.close();
			if (conn !=null)conn.close();
			
			if (sc !=null)sc.close();
			
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
	
	
	
	
	
	
	}
}

