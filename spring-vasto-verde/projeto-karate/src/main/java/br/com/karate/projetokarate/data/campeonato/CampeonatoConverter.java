package br.com.karate.projetokarate.data.campeonato;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.karate.projetokarate.data.pessoa.Pessoa;
import br.com.karate.projetokarate.model.atleta.AtletaCampOutput;
import br.com.karate.projetokarate.model.campeonato.CampeonatoDto;
import br.com.karate.projetokarate.model.campeonato.CampeonatoSaveInput;

public class CampeonatoConverter {

	@SuppressWarnings("deprecation")
	public static Campeonato toRec(CampeonatoSaveInput payload, List<Pessoa> pessoas) {
		Campeonato camp = new Campeonato();
		camp.setTitulo(payload.getTitulo());
		camp.setDescricao(payload.getDescricao());
		camp.setEndereco(payload.getEndereco());
		camp.setDataInicio(LocalDateTime.of(payload.getDataInicio().getYear(), payload.getDataInicio().getMonth(),
				payload.getDataInicio().getDay(), payload.getDataInicio().getHours(),
				payload.getDataInicio().getMinutes()));

		if (payload.getDataFim() != null)
			camp.setDataFim(LocalDateTime.of(payload.getDataFim().getYear(), payload.getDataFim().getMonth(),
					payload.getDataFim().getDay(), payload.getDataFim().getHours(), payload.getDataFim().getMinutes()));
		camp.setPessoas(pessoas);

		return camp;
	}

	public static CampeonatoDto toDto(Campeonato campeonato) {
		CampeonatoDto camp = new CampeonatoDto();

		camp.setTitulo(campeonato.getTitulo());
		camp.setDescricao(campeonato.getDescricao());
		camp.setDataInicio(LocalDateTime.parse(campeonato.getDataInicio().toString(),
				DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

		camp.setEndereco(campeonato.getEndereco());
		if (campeonato.getDataFim() != null)
			camp.setDataFim(LocalDateTime.parse(campeonato.getDataFim().toString(),
					DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

		List<AtletaCampOutput> atletas = new ArrayList<>();
//		campeonato.getAtletas().stream().forEach(a -> atletas.add(AtletaConverter.toCamp(a)));
//		campeonato.getPessoas().stream().forEach(pessoas -> );
		// TODO Corrigir este trecho de código

		camp.setAtletas(atletas);
		return camp;
	}
}
