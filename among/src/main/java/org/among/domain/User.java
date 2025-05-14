package org.among.domain;

import java.time.LocalDateTime;

public class User {
	private String id;
	
	private String password;
	
	private String name;
	
	/**
	 * 권한의 확장 등 고려 안해서 따로 권한 테이블 생성 X
	 * 코드도 없이 회원 테이블에 직접 권한 문자열 저장
	 */
	private Role role = Role.USER;
	
	/**
	 * MySQL의 DATETIME(타임존X) 혹은 TIMESTAMP 변환
	 */
	private LocalDateTime joinDateTime;
	
	private LocalDateTime lastModifiedDateTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDateTime getJoinDateTime() {
		return joinDateTime;
	}

	public void setJoinDateTime(LocalDateTime joinDateTime) {
		this.joinDateTime = joinDateTime;
	}

	public LocalDateTime getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public enum Role {
		ADMIN, USER;
		
		public static Role from(String value) {
			return Role.valueOf(value.toUpperCase());
		}
	}
}
