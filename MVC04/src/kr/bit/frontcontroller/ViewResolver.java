package kr.bit.frontcontroller;

public class ViewResolver { // prefix + nextPage + subfix 반환
	public static String makeView(String nextPage) {
		return "/WEB-INF/member/"+nextPage+".jsp";
	}
}
