package org.among.dto;

import org.among.domain.User;

public class UserResDto {
	private String id;
	
	private String name;
	
	private String role;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}
	
	public UserResDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.role = user.getRole().toString();
	}
}
