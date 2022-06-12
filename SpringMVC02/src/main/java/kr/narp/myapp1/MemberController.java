package kr.narp.myapp1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.bit.mapper.MemberMapper;
import kr.bit.model.MemberVO;
// @ ->어노테이션(전처리) -> Spring Container에서 관리를 해준다.
@Controller
public class MemberController {
    
	@Autowired
	//private MemberDAO dao; // DI
	private MemberMapper memberMapper;
	
	@RequestMapping("/memberList.do")
	public String memberList(Model model) { // HttpServletRequest == Model
		List<MemberVO> list=memberMapper.memberList();
		// 객체바인딩
		model.addAttribute("list", list);		
		return "memberList"; 
	}
	
	@RequestMapping("/memberInsert.do")
	public String memberInsert(MemberVO vo) { // 파라메터 수집(VO)
		// 인코딩(한글깨지는 부분?) spring web mvc 한글 깨짐(구글검색)
		int cnt=memberMapper.memberInsert(vo);
				
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberRegister.do")
	public String memberRegister() {
		
		return "memberRegister";
	}
	
	@RequestMapping("/memberDelete.do")
	public String memberDelete(@RequestParam("num") int num) { // 파라메터를 수집 : num
		int cnt=memberMapper.memberDelete(num);
		
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberContent.do")
	public String memberContent(int num, Model model) {
		
		MemberVO vo=memberMapper.memberContent(num);
		//객체바인딩
		model.addAttribute("vo", vo);
		
		return "memberContent";
	}
	
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(MemberVO vo) {
		
		int cnt=memberMapper.memberUpdate(vo);
		
		return "redirect:/memberList.do";
	}
}
