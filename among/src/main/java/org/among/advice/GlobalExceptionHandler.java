package org.among.advice;

import org.among.exception.PermissionDeniedException;
import org.among.exception.UserDuplicatedException;
import org.among.exception.UserNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({UserNotFoundException.class, UserDuplicatedException.class, PermissionDeniedException.class})
	public String handleUserExceptions(RuntimeException e, Model model) {
		model.addAttribute("errorMessage", e.getMessage());
		
		// 이 페이지 전용 JS 경로를 설정
		model.addAttribute("pageScript", "/resources/js/error.js");
	    
		return ".tiles/error";
	}
}
