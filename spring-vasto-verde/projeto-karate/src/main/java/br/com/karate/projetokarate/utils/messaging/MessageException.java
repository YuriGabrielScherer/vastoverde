package br.com.karate.projetokarate.utils.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.karate.projetokarate.pessoa.PessoaService;

public class MessageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ErrorCategory errorCategory;

	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaService.class);
	
	public MessageException(ErrorCategory category, String message) {
		super(message, null);
		this.errorCategory = category;
	}
	
	public MessageException(ErrorCategory category, String message, String titulo) {
		super(message, null);
		this.errorCategory = category;
	}
	
	public MessageException(ErrorCategory category, String message, Throwable cause, String messageErro) {
		super(messageErro, cause);
		LOGGER.error(messageErro, cause);
		this.errorCategory = category;
	}
	
	
	
	
	
}
