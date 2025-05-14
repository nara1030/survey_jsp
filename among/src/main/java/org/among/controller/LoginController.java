package org.among.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.among.domain.AutoLogin;
import org.among.dto.LoginReqDto;
import org.among.dto.UserResDto;
import org.among.service.LoginService;
import org.among.util.Utility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	public static final String PERSISTENT_COOKIE_NAME = "remember-me";
	
	public static final String AUTO_LOGIN = "Y";
	
	private final LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/login")
	public String login(HttpSession httpSession) {
		Object loginUser = httpSession.getAttribute("loginUser");
		if (loginUser != null) { // 세션 존재할 경우
			/*
			 * return ".tiles/main";
			 * 위와 같은 형식은 포워딩이므로 URL이 여전히 /login이라 안됨
			 * 사용자 혼란 및 새로고침시 재요청 이슈
			 */
			return "redirect:/";
		}
		
		return ".tiles/login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession httpSession, LoginReqDto loginReqDto, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		UserResDto userResDto = loginService.login(loginReqDto);
		httpSession.setAttribute("loginUser", userResDto);
		
		// 지속적 쿠키 설정(로그인 상태유지 체크시)
		if (AUTO_LOGIN.equals(loginReqDto.getAutoLoginYn())) {
			AutoLogin autoLogin = new AutoLogin();
			String token = UUID.randomUUID().toString();
			autoLogin.setToken(token);
			autoLogin.setUserId(userResDto.getId());
			autoLogin.setDeviceInfo(Utility.extractDeviceInfo(httpServletRequest.getHeader("User-Agent")));
			autoLogin.setIpAddress(Utility.getClientIp(httpServletRequest));
			loginService.saveToken(autoLogin);
			
			// 쿠키 생성
			Cookie cookie = new Cookie(PERSISTENT_COOKIE_NAME, token);
			cookie.setMaxAge(1 * 24 * 60 * 60); // 1일
			cookie.setPath("/");
			cookie.setHttpOnly(true); // XSS 방지(JS 접근 불가)
//			cookie.setSecure(true); // HTTPS로만 전송
			
			httpServletResponse.addCookie(cookie);
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession httpSession, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		Object loginUser = httpSession.getAttribute("loginUser");
		if (loginUser == null) { // 세션 존재하지 않을 경우(URL 접근 없을 거 같지만..)
			return "redirect:/";
		}
		
	    // 토큰 삭제
	    UserResDto userResDto = (UserResDto) httpSession.getAttribute("loginUser");
	    AutoLogin autoLogin = new AutoLogin();
	    autoLogin.setUserId(userResDto.getId());
	    autoLogin.setDeviceInfo(Utility.extractDeviceInfo(httpServletRequest.getHeader("User-Agent")));
	    loginService.deleteToken(autoLogin);
		
	    // 세션 삭제
		httpSession.invalidate();
		
		// 쿠키 삭제
		Cookie cookie = new Cookie(PERSISTENT_COOKIE_NAME, null);
	    cookie.setMaxAge(0);
	    cookie.setPath("/");
	    httpServletResponse.addCookie(cookie);
	    
		return "redirect:/";
	}
}
