package br.com.karate.projetokarate.atletas;

public class AtletaConverter {
	
	public AtletasDto toDto(Atletas obj) {
		AtletasDto atleta = new AtletasDto();
		
		atleta.setPessoaAtleta(obj.getPessoaAtleta());
		atleta.setGraduacao(obj.getGraduacao());
		
		return atleta;		
	}			
}
