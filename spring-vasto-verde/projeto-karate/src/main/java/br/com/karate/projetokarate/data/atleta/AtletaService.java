package br.com.karate.projetokarate.data.atleta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.karate.projetokarate.data.pessoa.PessoaService;
import br.com.karate.projetokarate.model.atleta.AtletaSaveInput;

@Service
public class AtletaService {

	@Autowired
	private PessoaService pessoaService;	
	
	public void delete(String cpf) {
		pessoaService.excluir(cpf);
	}
	
	
}
