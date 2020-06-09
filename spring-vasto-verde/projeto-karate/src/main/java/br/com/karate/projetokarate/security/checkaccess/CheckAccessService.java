package br.com.karate.projetokarate.security.checkaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.karate.projetokarate.security.login.JwtUtil;

@Service
public class CheckAccessService {

	@Autowired
	private JwtUtil jwtUtil;
	
	public String getUsuarioLogado(String token) {
		return this.jwtUtil.extractUsername(token);
	}
	
	
}
