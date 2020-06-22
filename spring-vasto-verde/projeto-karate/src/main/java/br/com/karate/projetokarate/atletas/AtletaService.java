package br.com.karate.projetokarate.atletas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AtletaService {

	@Autowired
	private AtletaRepository repository;
	
	public List<AtletasDto> findAll() {
		return repository.findAll();
	}
	
	public AtletasDto findById(Long id) {
		java.util.Optional<AtletasDto> obj = repository.findById(id);
		return obj.get();
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
