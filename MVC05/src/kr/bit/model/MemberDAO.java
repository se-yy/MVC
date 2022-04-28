package kr.bit.model;
import java.io.InputStream;
// JDBC->myBatis
import java.sql.*;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
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
}


