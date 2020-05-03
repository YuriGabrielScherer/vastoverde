package br.com.karate.projetokarate.data.associacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.karate.projetokarate.model.associacao.AssociacaoDto;

public class AssociacaoConverter {

	public static AssociacaoDto toDto(Associacao payload) {
		AssociacaoDto output = new AssociacaoDto();

		output.setId(payload.getId());
		output.setNome(payload.getNome());
		output.setCidade(payload.getCidade());
		output.setEndereco(payload.getEndereco());
		output.setResponsavel(payload.getResponsavel());
		output.setTelefone(payload.getTelefone());

		return output;
	}

	public static List<AssociacaoDto> toDto(Page<Associacao> page) {
		List<AssociacaoDto> list = new ArrayList<>();
		page.getContent().stream().forEach(a -> {
			list.add(toDto(a));
		});

		return list;

	}
}
