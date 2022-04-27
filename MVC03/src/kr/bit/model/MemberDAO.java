package kr.bit.model;
// JDBC(db연결) ~> 나중에는 myBatis, JPA

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {
	private Connection conn; // db와 연결
	private PreparedStatement pstmt; // sql 전송
	private ResultSet rs; // db에 있는 데이터 가져와서 저장(보관)
	
	// 1. db 연결 객체 생성
	public void getConnect() {
		// test db에 접속
		String url="jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC";
		String user="root";
		String password="1053";
		
		// MySQL Driver 로딩
		// mysql driver에서 해당 클래스 찾아서 로딩
		try {
			// 동적 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// driver에 접속 시도
			// 접속 성공 시 연결 정보를 넘겨줌
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// db 연결 종료 (메모리 효율적 사용 위해)
	// 사용한 역순으로 종료
	public void dbClose() {
		try {
			// rs는 사용할 때도 있고 안할때도 있기 때문에 만들어져있는 경우에만 close
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원 저장 (insert)
	public int memberInsert(MemberVO vo) {
		int cnt = -1;
		String sql = "insert into member(id, pass, name, age, email, phone) "
				+ "values(?, ?, ?, ?, ?, ?)";
		
		// 연결 객체 생성
		getConnect();
		
		// SQL문장을 전송하는 객체 생성
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setInt(4, vo.getAge());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getPhone());
			
			// 완성된 sql 전송
			// 성공한 행의 개수 반환 (성공->1이상, 실패->0)
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행하는 block
			dbClose();
		}
		return cnt;
	}
	// 회원 삭제 (delete)
	public int memberDelete(int num) {
		String sql = "delete from member "
				+ "where num=?";
		int cnt = -1;
		
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	// 회원 정보 업데이트
	public int memberUpdate(MemberVO vo) {
		String sql = "update member "
				+ "set age=?, email=?, phone=? "
				+ "where num=?";
		int cnt = -1;
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getAge());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPhone());
			pstmt.setInt(4, vo.getNum());
			
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}
	
	// 회원전체 리스트 가져오는 메서드
	// 회원 하나 -> VO, 회원 전체 -> ArrayList
	public ArrayList<MemberVO> memberList() {
		String sql = "select * from member";
		getConnect();
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // rs : 커서
			while(rs.next()) {
				int num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				// 묶고
				MemberVO vo = new MemberVO(num, id, pass, name, age, email, phone);
				// 담고
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}
	
	// 해당되는 회원 한명의 정보
	public MemberVO memberContent(int num) {
		String sql = "select * from member "
				+ "where num=?";
		MemberVO vo = null;
		getConnect();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if (rs.next()) { // 데이터가 있으면
				// 회원 한 명의 정보를 가져와서 묶고(vo에)
				num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				vo = new MemberVO(num, id, pass, name, age, email, phone);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vo;
	}
}
