package br.com.karate.projetokarate.data.campeonato;

import java.util.ArrayList;
import java.util.List;

import br.com.karate.projetokarate.data.atleta.Atleta;
import br.com.karate.projetokarate.data.atleta.AtletaConverter;
import br.com.karate.projetokarate.model.atleta.AtletaCampOutput;
import br.com.karate.projetokarate.model.campeonato.CampeonatoDto;
import br.com.karate.projetokarate.model.campeonato.CampeonatoSaveInput;

public class CampeonatoConverter {

	public static Campeonato toRec(CampeonatoSaveInput payload, List<Atleta> atletas) {
		Campeonato camp = new Campeonato();
		camp.setTitulo(payload.getTitulo());
		camp.setDescricao(payload.getDescricao());
		camp.setEndereco(payload.getEndereco());
		camp.setDataInicio(payload.getDataInicio());

		if (payload.getDataFim() != null)
			camp.setDataFim(payload.getDataFim());
		camp.setAtletas(atletas);

		return camp;
	}

	public static CampeonatoDto toDto(Campeonato campeonato) {
		CampeonatoDto camp = new CampeonatoDto();

		camp.setTitulo(campeonato.getTitulo());
		camp.setDescricao(campeonato.getDescricao());
		camp.setDataInicio(campeonato.getDataInicio());
		camp.setEndereco(campeonato.getEndereco());
		if (campeonato.getDataFim() != null)
			camp.setDataFim(campeonato.getDataFim());

		List<AtletaCampOutput> atletas = new ArrayList<>();
		campeonato.getAtletas().stream().forEach(a -> atletas.add(AtletaConverter.toCamp(a)));

		camp.setAtletas(atletas);
		return camp;
	}
}
