package br.com.karate.projetokarate.security.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.data.pessoa.PessoaService;
import br.com.karate.projetokarate.security.filter.JwtRequestFilter;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/login")
public class AuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping
	public ResponseEntity<?> login (@RequestBody AuthenticationInput payload) {
		
		try {
			payload.setPassword(encoder.encode(payload.getPassword()));
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword())
			);
		} catch (AuthenticationException e) {
			LOGGER.warn("Erro ao autenticar..." + e.getMessage());
			
		}
		
		final UserDetails userDetails = this.pessoaService.loadUserByUsername(payload.getUsername());
		final String token = this.jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationOutput(token));
	}

}
