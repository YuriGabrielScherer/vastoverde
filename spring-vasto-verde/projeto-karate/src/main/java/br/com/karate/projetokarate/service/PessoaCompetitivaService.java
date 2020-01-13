package br.com.karate.projetokarate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.model.AtletaPessoaCompetitivaModelo;
import br.com.karate.projetokarate.model.PessoaCompetitivaModelo;
import br.com.karate.projetokarate.repository.PessoaCompetitivaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PessoaCompetitivaService {

    @Autowired
    private PessoaCompetitivaRepository pessoaCompetitivaRepository;

    public int savePessoaCompetitiva(AtletaPessoaCompetitivaModelo objeto) {

        PessoaCompetitivaModelo pessoaCompetitiva = new PessoaCompetitivaModelo();
        try {

            // Criando o Objeto pessoaCompetitiva
            pessoaCompetitiva.setId(objeto.getIdPessoaCompetitiva());
            pessoaCompetitiva.setIdGrau(objeto.getIdGrau());
            pessoaCompetitiva.setIdPessoa(objeto.getIdPessoa());
            pessoaCompetitiva.setDataInicio(objeto.getDataInicio());

            // If - Federacao e Confederacao
            if (objeto.getConfederacao() != 0)
                pessoaCompetitiva.setConfederacao(objeto.getConfederacao());

            if (objeto.getFederacao() != 0)
                pessoaCompetitiva.setFederacao(objeto.getFederacao());

            // Salvando a Pessoa Competitiva
            this.pessoaCompetitivaRepository.save(pessoaCompetitiva);

            return pessoaCompetitiva.getId();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return -1;
        }

    }

    public boolean excluir(int idPessoaCompetitiva) {

        try {

            // Find Pessoa Competitiva
            PessoaCompetitivaModelo pessoa = this.pessoaCompetitivaRepository.findById(idPessoaCompetitiva);
            
            // Tentando excluir a Pessoa Competitiva
            this.pessoaCompetitivaRepository.delete(pessoa);

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir PessoaCompetitiva -> " + e.getMessage());
            return false;
        }

    }

}