package br.com.karate.projetokarate.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptografarSenha {
	
	private BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
	
	public String criptografar(String senha){
		String senhaC = crypt.encode(senha);
		return senhaC;
	}
		

}
