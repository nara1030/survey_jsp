package org.among.util;

import javax.servlet.http.HttpServletRequest;

public class Utility {
	/**
	 * IP 주소 추출
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0]; // 다중 프록시 대응
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
	}
	
	/**
	 * 이 메소드만 따로 테스트 필요
	 * Ex. PC: 크롬, 익스플로러, 사파리
	 *     모바일: 크롬,
	 */
	public static String extractDeviceInfo(String userAgentString) {
		String osName = "Unknown OS";
	    String browserName = "Unknown Browser";
	    String deviceCategory = "Unknown Device";

	    // OS 정보 추출
	    if (userAgentString.contains("Windows NT 10.0")) {
	        osName = "Windows 10";
	    } else if (userAgentString.contains("Windows NT 6.1")) {
	        osName = "Windows 7";
	    } else if (userAgentString.contains("Android")) {
	        osName = "Android";
	    } else if (userAgentString.contains("iPhone")) {
	        osName = "iOS";
	    }

	    // 브라우저 정보 추출
	    if (userAgentString.contains("Chrome") && !userAgentString.contains("Chromium")) {
	        browserName = "Chrome";
	    } else if (userAgentString.contains("MSIE") || userAgentString.contains("Trident")) {
	        browserName = "Internet Explorer";
	    } else if (userAgentString.contains("Firefox")) {
	        browserName = "Firefox";
	    } else if (userAgentString.contains("Safari") && !userAgentString.contains("Chrome")) {
	        browserName = "Safari";
	    }

	    // 디바이스 유형 추출
	    if (userAgentString.contains("Mobile")) {
	        deviceCategory = "Mobile";
	    } else if (userAgentString.contains("Tablet")) {
	        deviceCategory = "Tablet";
	    } else {
	        deviceCategory = "Computer";
	    }

	    return osName + " | " + browserName + " | " + deviceCategory;
    }
}
