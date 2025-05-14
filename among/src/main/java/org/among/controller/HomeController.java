package org.among.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String main(Model model) {
		// JSP에서 세션 검사해서 메뉴 노출 다르게 해줌
		model.addAttribute("title", "메인 페이지");
		return ".tiles/main";
	}
}
