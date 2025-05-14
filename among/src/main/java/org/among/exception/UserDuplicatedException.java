package org.among.exception;

public class UserDuplicatedException extends RuntimeException {
	public UserDuplicatedException(String message) {
        super(message);
    }
}
