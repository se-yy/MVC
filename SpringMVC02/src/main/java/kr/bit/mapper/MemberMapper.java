package kr.bit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.bit.model.MemberVO;

// SQL id = "메서드 이름"
//@Mapper
public interface MemberMapper {
	public List<MemberVO> memberList();
	public int memberInsert(MemberVO vo);
	public int memberDelete(int num);
	public MemberVO memberContent(int num);
	public int memberUpdate(MemberVO vo);
}

/*
// annotation 이용한 방법
public interface MemberMapper {
	@Select("select * from member")
	public List<MemberVO> memberList();
	
	@Insert("insert into member(id, pass, name, age, email, phone)
        values(
        #{id},
        #{pass},
        #{name},
        #{age},
        #{email},
        #{phone}
        ) ")
	public int memberInsert(MemberVO vo);
	
	@Delete("delete from member where num=#{num}")
	public int memberDelete(int num);
	
	@Select("select * from member where num=#{num}")
	public MemberVO memberContent(int num);
	
	@Update("update member 
         set age=#{age}, email=#{email}, phone=#{phone} 
         where num=#{num}")
	public int memberUpdate(MemberVO vo);
}
*/