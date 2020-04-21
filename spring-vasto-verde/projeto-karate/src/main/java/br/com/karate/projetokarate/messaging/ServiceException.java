package br.com.karate.projetokarate.messaging;

public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ErrorCategory errorCategory;
	
	private final String errorCode;
	
	private final Object customPayload;

	public ServiceException(ErrorCategory category, String message, String errorCode, Throwable cause, Object customPayload) {
		super(message, cause);
		this.errorCode = errorCode;
		this.errorCategory = category;
		this.customPayload = customPayload;
	}
	
	public ServiceException(ErrorCategory category, String message, Throwable cause, Object customPayload) {
		super(message, cause);
		this.errorCode = null;
		this.errorCategory = category;
		this.customPayload = customPayload;
	}
	
	public ServiceException(ErrorCategory category, String message, Throwable cause) {
		this(category, message, cause, null);
	}
	
	public ServiceException(ErrorCategory category, String message, String errorCode) {
		this(category, message, errorCode, null, null);
	}
	
	public ServiceException(ErrorCategory category, String message, String errorCode, Throwable cause) {
		this(category, message, errorCode, cause, null);
	}
	
	public ServiceException(ErrorCategory category, String message) {
		this(category, message, null, null, null);
	}
	
	public ErrorCategory getCategory() {
		return errorCategory;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public Object getCustomPayload() {
		return customPayload;
	}
	
	
	
	
	
}
