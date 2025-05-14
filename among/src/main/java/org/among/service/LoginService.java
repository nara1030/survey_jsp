package org.among.service;

import java.time.LocalDateTime;

import org.among.dao.UserDao;
import org.among.domain.AutoLogin;
import org.among.domain.User;
import org.among.dto.LoginReqDto;
import org.among.dto.UserResDto;
import org.among.exception.ErrorCode;
import org.among.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	private final UserDao userDao;
	
	public LoginService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserResDto login(LoginReqDto loginReqDto) {
		User user = userDao.findUser(loginReqDto.toUser());
		if (user == null) {
			throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage());
		}
		
		UserResDto userResDto = new UserResDto(user);
		return userResDto;
	}
	
	public void saveToken(AutoLogin autoLogin) {
		String expiredToken = userDao.findExpiredToken(autoLogin);
		
		autoLogin.setExpiredDate(LocalDateTime.now().plusDays(1));
		if (expiredToken != null) { // 만료된 토큰이 이미 저장되어 있는 경우
			userDao.updateToken(autoLogin);
		} else {
			userDao.insertToken(autoLogin);
		}
	}
	
	public UserResDto findUserByToken(String token) {
		AutoLogin autoLogin = new AutoLogin();
		autoLogin.setToken(token);
		User user = userDao.findUserByToken(autoLogin);
		if (user == null) { // 잘못된 토큰입니다?
			throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION.getMessage());
		}
		
		UserResDto userResDto = new UserResDto(user);
		return userResDto;
	}
	
	/**
	 * 로그아웃시 토큰 삭제
	 * 이 과정이 없다면 세션은 없는데, 토큰만 있어서 로그인 에러(UserNotFoundException)
	 */
	public void deleteToken(AutoLogin autoLogin) {
		userDao.deleteToken(autoLogin);
	}
}
