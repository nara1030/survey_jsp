package org.among.service;

import java.sql.SQLIntegrityConstraintViolationException;

import org.among.dao.UserDao;
import org.among.domain.User;
import org.among.dto.SignupReqDto;
import org.among.exception.ErrorCode;
import org.among.exception.UserDuplicatedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
	private final UserDao userDao;
	
	public JoinService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void signup(SignupReqDto signupReqDto) {
		User user = signupReqDto.convertToUser();
		try {
			userDao.insertUser(user);
		} catch (DataIntegrityViolationException e) {
			Throwable cause = e.getCause();
			if (cause instanceof SQLIntegrityConstraintViolationException) {
				throw new UserDuplicatedException(ErrorCode.USER_DUPLICATED_EXCEPTION.getMessage());
			}
			throw e; // 이건 따로 처리 안해줌
		}
	}
	
	public void withdraw() {
		
	}

}
