package br.com.karate.projetokarate.utils.messaging;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageService {

	public static ResponseEntity<String> send(HttpStatus codigo, String mensagem, String titulo) {
		HttpHeaders header = new HttpHeaders();		
		header.add("title", titulo);
				
		return new ResponseEntity<String>(mensagem, header, codigo);
	}

	
}
