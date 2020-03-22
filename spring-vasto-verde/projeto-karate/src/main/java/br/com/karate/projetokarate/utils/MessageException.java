package br.com.karate.projetokarate.utils;

import org.springframework.http.HttpStatus;


public class MessageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final HttpStatus categoria;
	private final String errorCode;
	
	public MessageException(HttpStatus category, String message, String errorCode, Throwable cause) {
		super(message, cause);
		this.categoria = category;
		this.errorCode = errorCode;
	}
	
	public MessageException(HttpStatus category, String message) {
		super(message, null);
		this.categoria = category;
		this.errorCode = null;
	}
	
	public MessageException(HttpStatus category, String message, Throwable cause) {
		super(message, cause);
		this.categoria = category;
		this.errorCode = null;
	}
	
	
	
}
