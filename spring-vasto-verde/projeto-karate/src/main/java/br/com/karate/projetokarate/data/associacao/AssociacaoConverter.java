package br.com.karate.projetokarate.data.associacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.karate.projetokarate.model.associacao.AssociacaoDto;
import br.com.karate.projetokarate.model.associacao.AssociacaoSaveInput;

@Component
public class AssociacaoConverter {

	public AssociacaoDto toDto(Associacao payload) {
		AssociacaoDto output = new AssociacaoDto();

		output.setNome(payload.getNome());
		output.setCodigo(payload.getCodigo());
		output.setCidade(payload.getCidade());
		output.setEndereco(payload.getEndereco());
		output.setResponsavel(payload.getResponsavel());
		output.setTelefone(payload.getTelefone());

		return output;
	}

	public Associacao toRec(AssociacaoSaveInput payload, Associacao a) {

		a.setNome(payload.getNome());
		a.setCodigo(payload.getCodigo());
		a.setCidade(payload.getCidade());
		a.setAtivo(true);

		// TODO Implementar o código para colocar Responsavel
		if (payload.getTelefone() != null) {
			a.setTelefone(payload.getTelefone());
		}

		if (payload.getEndereco() != null) {
			a.setEndereco(payload.getEndereco());
		}

		return a;
	}

	public List<AssociacaoDto> toDto(Page<Associacao> page) {
		List<AssociacaoDto> list = new ArrayList<>();
		page.getContent().stream().forEach(a -> {
			list.add(toDto(a));
		});

		return list;

	}
}
