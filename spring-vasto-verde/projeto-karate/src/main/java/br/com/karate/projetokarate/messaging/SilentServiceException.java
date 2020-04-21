package br.com.karate.projetokarate.messaging;

public class SilentServiceException extends ServiceException {
	
	private static final long serialVersionUID = 1L;

	public SilentServiceException(ErrorCategory category, String message, String errorCode, Throwable cause, Object customPayload) {
		super(category, message, errorCode, cause, customPayload);
	}
	
	public SilentServiceException(ErrorCategory category, String message, Throwable cause, Object customPayload) {
		super(category, message, cause, customPayload);
	}

	public SilentServiceException(ErrorCategory category, String message, Throwable cause) {
		super(category, message, cause);
	}
	
	public SilentServiceException(ErrorCategory category, String message, String errorCode) {
		super(category, message, errorCode);
	}
	
	public SilentServiceException(ErrorCategory category, String message, String errorCode, Throwable cause) {
		super(category, message, errorCode, cause);
	}
	
	public SilentServiceException(ErrorCategory category, String message) {
		super(category, message);
	}
	
}
