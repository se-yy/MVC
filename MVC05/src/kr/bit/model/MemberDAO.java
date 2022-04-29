package kr.bit.model;
import java.io.InputStream;
// JDBC->myBatis
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	private static SqlSessionFactory sqlSessionFactory;
	
	// 초기화 블럭 - 프로그램 실행 시 딱 한번만 실행되는 코드 영역
	static {
		try {
			String resource = "kr/bit/mybatis/config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource); // xml 읽기(Input)
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); // connection pool 생성
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원 전체 리스트 보기
	public List<MemberVO> memberList() {
		// JDBC의 Connection(db연결) + Statement(sql전송) => SqlSession
		SqlSession session = sqlSessionFactory.openSession(); // 연결 객체 하나 받아옴
		
		// Mapper 파일의 SQL 사용하기위해 연동 후 결과 값 받음
		List<MemberVO> list = session.selectList("memberList");
		
		session.close(); // 반납
		return list;
	}
	
	// 회원 가입
	public int memberInsert(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.insert("memberInsert", vo);
		session.commit();
		session.close();
		return cnt;
	}
	
	// 회원 삭제
	public int memberDelete(int num) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.delete("memberDelete", num);
		session.commit();
		session.close();
		return cnt;
	}
	
	// 회원 상세보기
	public MemberVO memberContent(int num) {
		SqlSession session = sqlSessionFactory.openSession();
		MemberVO vo = session.selectOne("memberContent", num);
		session.close();
		return vo;
	}
	
	// 회원 수정하기
	public int memberUpdate(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.update("memberUpdate", vo);
		session.commit();
		session.close();
		return cnt;
	}
}


