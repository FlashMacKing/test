package com.akampany.api.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
public UserAlreadyExistsException(String message) {
	super(message);
}
}
