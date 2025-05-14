package org.among.controller;

import javax.servlet.http.HttpSession;

import org.among.dto.SignupReqDto;
import org.among.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {
	private final JoinService joinService;
	
	public JoinController(JoinService joinService) {
		this.joinService = joinService;
	}

	@GetMapping("/signup")
	public String signup(HttpSession httpSession) {
		Object loginUser = httpSession.getAttribute("loginUser");
		if (loginUser != null) { // 세션 존재할 경우
			return "redirect:/";
		}
		
		return ".tiles/signup";
	}
	
	@PostMapping("/signup")
	public String signup(SignupReqDto signupReqDto) {
		joinService.signup(signupReqDto);
		
		return "redirect:/";
	}
	
	// 회원 탈퇴
	@PostMapping("/withdraw")
	public String withdraw(SignupReqDto signupReqDto) {
		joinService.withdraw();
		
		return "redirect:/";
	}
}
