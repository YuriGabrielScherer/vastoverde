package br.com.karate.projetokarate.atleta;

import br.com.karate.projetokarate.associacao.Associacao;
import br.com.karate.projetokarate.pessoa.Pessoa;
import br.com.karate.projetokarate.pessoa.PessoaConverter;

public class AtletaConverter {

	public static AtletaDto toDto(Atleta atleta) {
		AtletaDto atletaDto = new AtletaDto();
		atletaDto.setCpfResponsavel(atleta.getCpfResponsavel());
		atletaDto.setNomeResponsavel(atleta.getNomeResponsavel());
		atletaDto.setTelefoneResponsavel(atleta.getTelefoneResponsavel());
		atletaDto.setPessoa(PessoaConverter.toDto(atleta.getPessoa()));
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
//		atleta.setCampeonatos(atleta2.getCampeonatos());
		return atleta;
	}

}
