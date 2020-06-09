package br.com.karate.projetokarate.security.checkaccess;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/checkAccess")
public class CheckAccessController {

	@PostMapping
	public ResponseEntity<String> getUsuarioLogado(HttpServletRequest request) {
		// TODO Implementar o Método CheckAccess para as guards do Front
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
}
