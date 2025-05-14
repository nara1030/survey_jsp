package org.among.controller;

import javax.servlet.http.HttpSession;

import org.among.dto.survey.ObjectiveReqDto;
import org.among.dto.survey.SubjectiveReqDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SurveyController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyController.class);

	@GetMapping("/register")
	public String register(HttpSession httpSession, Model model) {
		Object loginUser = httpSession.getAttribute("loginUser");
		if (loginUser == null) { // 세션 없는 경우
			return "redirect:/";
		}
		
		// 이 페이지 전용 JS 경로를 설정
		model.addAttribute("pageScript", "/resources/js/survey.js");
		
		return ".tiles/survey";
	}
	
	@PostMapping("/register/objective")
	public String register(HttpSession httpSession, ObjectiveReqDto objectiveReqDto, Model model) {
		Object loginUser = httpSession.getAttribute("loginUser");
		if (loginUser == null) { // 세션 없는 경우
			return "redirect:/";
		}
		
		// 설문 프로세스
		
		model.addAttribute("pageScript", "/resources/js/submitted.js");
		
		return ".tiles/submitted";
	}
	
	@PostMapping("/register/subjective")
	public String register(HttpSession httpSession, SubjectiveReqDto subjectiveReqDto, Model model) {
		Object loginUser = httpSession.getAttribute("loginUser");
		if (loginUser == null) { // 세션 없는 경우
			return "redirect:/";
		}
		
		LOGGER.info("생성할 설문조사 요청 객체: {}", subjectiveReqDto);
		
		// 설문 프로세스
		
		model.addAttribute("pageScript", "/resources/js/submitted.js");
		
		return ".tiles/submitted";
	}
}
