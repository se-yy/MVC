package kr.narp.myapp;

import org.springframework.web.bind.annotation.RequestMapping;

public class MemberController {
	
	@RequestMapping("/memberList.do")
	public String memberList() {
		
		
		return "memberList";
	}
	
	@RequestMapping("/memberInsert.do")
	public String memberInsert() {
		
		
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberRegister.do")
	public String memberRegister() {
		
		
		return "memberRegister";
	}
}
