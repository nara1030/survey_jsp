package org.among.dto;

import org.among.domain.User;

public class LoginReqDto {
	private String email;
	
	private String password;
	
	private String autoLoginYn;
	
	private String deviceInfo;
	
	private String ipAddress;
	
	public User toUser() {
		User user = new User();
		user.setId(this.email);
		user.setPassword(this.password);
		
		return user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAutoLoginYn() {
		return autoLoginYn;
	}

	public void setAutoLoginYn(String autoLoginYn) {
		this.autoLoginYn = autoLoginYn;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
