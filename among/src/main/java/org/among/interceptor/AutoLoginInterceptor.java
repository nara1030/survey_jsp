package org.among.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.among.dto.UserResDto;
import org.among.service.LoginService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AutoLoginInterceptor implements HandlerInterceptor {
	public static final String PERSISTENT_COOKIE_NAME = "remember-me";
	
	private final LoginService loginService;
	
	public AutoLoginInterceptor(LoginService loginService) {
		this.loginService = loginService;
	}
	
	/**
	 * 세션이 있다면 통과, 없더라도 자동 로그인 쿠키 존재시 세션 생성(로그인 처리)
	 * 이후 세션 기반으로 개별 컨트롤러에서 직접 URL 접근 통제
	 * 
	 * 로그인 상태는 두 가지로 확인
	 * 1) 세션 존재하는 경우
	 * 2) 세션 존재하지 않지만 쿠키에 유효한 토큰 존재하는 경우
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션 존재할 경우
		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			Object loginUser = httpSession.getAttribute("loginUser");
			if (loginUser != null) {
				return true;
			}
		}
		
		// 세션 존재하지 않지만 자동 로그인 쿠키 존재할 경우
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (PERSISTENT_COOKIE_NAME.equals(cookie.getName())) {
					String token = cookie.getValue();
					
					// 토큰으로 사용자 조회
					UserResDto userResDto = loginService.findUserByToken(token);
					if (userResDto != null) {
						httpSession = request.getSession(true); // 세션 생성
						httpSession.setAttribute("loginUser", userResDto);
						return true;
					}
				}
			}
		}
		// 세션 존재하지 않거나 쿠키 없더라도 인터셉터 통과
		return true;
	}
}
