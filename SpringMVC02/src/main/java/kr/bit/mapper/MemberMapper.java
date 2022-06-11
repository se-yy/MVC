package kr.bit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.bit.model.MemberVO;

// SQL id = "메서드 이름"
@Mapper
public interface MemberMapper {
	public List<MemberVO> memberList();
	public int memberInsert(MemberVO vo);
	public int memberDelete(int num);
	public MemberVO memberContent(int num);
	public int memberUpdate(MemberVO vo);
}
