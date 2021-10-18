package com.medicare.backend.exception;

/**
 * @author fsd developer:  kevin casey
 *
 */
public class UserFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserFoundException() {
        super("That 'username' is already taken, please choose another name");
    }

    public UserFoundException(String msg) {
        super(msg);
    }
}
