package org.among.interceptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.among.dto.UserResDto;
import org.among.exception.ErrorCode;
import org.among.exception.PermissionDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	private static final Set<String> ADMIN_ONLY_PATHS = new HashSet<>(Arrays.asList("/register", "/register/objective", "/register/subjective"));
	
	/**
	 * 로그인, 즉 세션이 존재하지만 메뉴별 권한 체크 용도
	 * 
	 * 회원 전용(로그인 필수) 메뉴 경우 개별 컨트롤러에서 세션 체크해주고 있으나,
	 * 권한별 적용이 필요한 서비스의 경우 컨트롤러에서 개별 작성한다면 관리 힘들기에 공통 추출
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI().substring(request.getContextPath().length());
		if (ADMIN_ONLY_PATHS.contains(uri)) { // ADMIN 권한이 필요한 URL
			HttpSession httpSession = request.getSession(false);
			if (httpSession != null) {
				Object loginUser = httpSession.getAttribute("loginUser");
				if (loginUser != null) { // 세션 존재하는 경우
					String role= ((UserResDto) loginUser).getRole();
					if (!"ADMIN".equals(role)) {
						throw new PermissionDeniedException(ErrorCode.PERMISSION_DENIED_EXCEPTION.getMessage());
						// 예외 핸들러에서 처리해주기 위해 return false 안해줌
					}
				}
			}
		}
		
		return true;
	}
}
