package org.among.exception;

public class PermissionDeniedException extends RuntimeException {
	public PermissionDeniedException(String message) {
        super(message);
    }

}
