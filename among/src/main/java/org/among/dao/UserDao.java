package org.among.dao;

import java.sql.SQLIntegrityConstraintViolationException;

import org.among.domain.AutoLogin;
import org.among.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
	User findUser(User user);
	
	User findUserByToken(AutoLogin autoLogin);
	
	int insertUser(User user);
	
	int insertToken(AutoLogin autoLogin);
	
	String findExpiredToken(AutoLogin autoLogin);
	
	int updateToken(AutoLogin autoLogin);
	
	void deleteToken(AutoLogin autoLogin);
}
