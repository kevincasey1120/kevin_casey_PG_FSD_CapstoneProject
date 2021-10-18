package com.medicare.backend.exception;

/**
 * @author fsd developer:  kevin casey
 *
 */
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
        super("<INVALID RECORD REQUEST>  That User Name Does Not Exist");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
