package hello.helloSpring.controller;

import hello.helloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

	private final MemberService memberService;

//	@Autowired
//	public void setMemberService(MemberService memberService) {
//		this.memberService = memberService;
//	}

	@Autowired //요즘 가장 권장하는 생성자 주입 방식
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
}
