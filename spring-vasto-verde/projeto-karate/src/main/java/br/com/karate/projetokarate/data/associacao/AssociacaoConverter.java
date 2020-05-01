package br.com.karate.projetokarate.data.associacao;

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
}
