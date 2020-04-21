package br.com.karate.projetokarate.data.campeonato;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.karate.projetokarate.data.atleta.Atleta;
import br.com.karate.projetokarate.data.atleta.AtletaService;
import br.com.karate.projetokarate.messaging.ErrorCategory;
import br.com.karate.projetokarate.messaging.ServiceException;
import br.com.karate.projetokarate.model.campeonato.CampeonatoSaveInput;

@Service
public class CampeonatoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CampeonatoService.class);

	@Autowired
	private CampeonatoRepository campeonatoRepository;

	@Autowired
	private AtletaService atletaService;

	@Autowired
	private CampeonatoValidator validar;

	public Campeonato findById(int id) {
		LOGGER.info("Buscando campeonato por ID...");
		Optional<Campeonato> camp = this.campeonatoRepository.findById(id);
		if (camp.isPresent())
			return camp.get();

		throw new ServiceException(ErrorCategory.BAD_REQUEST, "Campeonato não encontrado com o ID especificado.",
				"Buscar Campeonato");
	}

	public ResponseEntity<?> save(CampeonatoSaveInput campeonato) {
		LOGGER.info("Salvando campeonato...");
		this.validar.validarCampeonato(campeonato);
		List<Atleta> atletas = new ArrayList<>();
		campeonato.getIdAtletas().stream().forEach(a -> atletas.add(this.atletaService.findById(a)));
		try {
			this.campeonatoRepository.save(CampeonatoConverter.toRec(campeonato, atletas));
			LOGGER.info("Campeonato salvo com sucesso...");
		} catch (Exception e) {
			LOGGER.error("Erro ao salvar o campeonato, " + e.getMessage());
			throw new ServiceException(ErrorCategory.BAD_REQUEST, "Erro ao salvar o campeonato.", e.getCause(),
					e.getMessage());
		}
		return new ResponseEntity<String>("Campeonato salvo com sucesso!", HttpStatus.CREATED);
	}
	
	
}
