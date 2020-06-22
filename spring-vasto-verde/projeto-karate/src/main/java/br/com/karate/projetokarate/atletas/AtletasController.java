package br.com.karate.projetokarate.atletas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class AtletasController {
	
	@Autowired
	private AtletaService service;
		
	@GetMapping(value = "/{id}")
	public ResponseEntity<AtletasDto> findById(@PathVariable Long id) {
		AtletasDto obj  = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<AtletasDto>> findAll() {
		List<AtletasDto> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
 	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
