package br.com.karate.projetokarate.data.atleta;

import br.com.karate.projetokarate.data.associacao.Associacao;
import br.com.karate.projetokarate.data.pessoa.Pessoa;
import br.com.karate.projetokarate.data.pessoa.PessoaConverter;
import br.com.karate.projetokarate.model.atleta.AtletaCampOutput;
import br.com.karate.projetokarate.model.atleta.AtletaDto;
import br.com.karate.projetokarate.model.atleta.AtletaSaveInput;

public class AtletaConverter {

	public static AtletaDto toDto(Atleta atleta) {
		AtletaDto atletaDto = new AtletaDto();
		atletaDto.setCpfResponsavel(atleta.getCpfResponsavel());
		atletaDto.setNomeResponsavel(atleta.getNomeResponsavel());
		atletaDto.setTelefoneResponsavel(atleta.getTelefoneResponsavel());
		atletaDto.setEndereco(atleta.getEndereco());
		atletaDto.setPessoa(PessoaConverter.toDto(atleta.getPessoa()));
		atletaDto.setCampeonatos(atleta.getCampeonatos());
		atletaDto.setAtivo(atleta.isAtivo());
		return atletaDto;
	}

	public static Atleta toRec(AtletaSaveInput atleta2, Pessoa pessoa, Associacao associacao) {
		Atleta atleta = new Atleta();
		atleta.setCpfResponsavel(atleta2.getCpfResponsavel());
		atleta.setNomeResponsavel(atleta2.getNomeResponsavel());
		atleta.setTelefoneResponsavel(atleta2.getTelefoneResponsavel());
		atleta.setEndereco(atleta2.getEndereco());
		atleta.setPessoa(pessoa);
		atleta.setAssociacao(associacao);
		atleta.setAtivo(true);
		return atleta;
	}

	public static AtletaCampOutput toCamp(Atleta payload) {
		AtletaCampOutput atleta = new AtletaCampOutput();

		atleta.setCpfResponsavel(payload.getCpfResponsavel());
		atleta.setNomeResponsavel(payload.getNomeResponsavel());
		atleta.setTelefoneResponsavel(payload.getTelefoneResponsavel());
		atleta.setPessoa(PessoaConverter.toCamp(payload.getPessoa()));
		atleta.setAssociacao(payload.getAssociacao());
		atleta.setAtivo(payload.isAtivo());
		return atleta;
	}

}
