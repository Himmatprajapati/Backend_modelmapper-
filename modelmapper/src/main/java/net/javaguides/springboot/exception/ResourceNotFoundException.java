package net.javaguides.springboot.exception;

import org.aspectj.bridge.Message;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFoundException extends Exception {
	
	private static final long  serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
