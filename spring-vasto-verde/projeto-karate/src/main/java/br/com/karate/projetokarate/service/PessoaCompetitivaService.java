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

    public int savePessoaCompetitiva(AtletaPessoaCompetitivaModelo objCadastrar) {

        PessoaCompetitivaModelo pessoaCompetitiva = new PessoaCompetitivaModelo();
        try {

            // Criando o Objeto pessoaCompetitiva
            pessoaCompetitiva.setIdGrau(objCadastrar.getIdGrau());
            pessoaCompetitiva.setIdPessoa(objCadastrar.getIdPessoa());
            pessoaCompetitiva.setDataInicio(objCadastrar.getDataInicio());

            // If - Federacao e Confederacao
            if (objCadastrar.getConfederacao() != 0)
                pessoaCompetitiva.setConfederacao(objCadastrar.getConfederacao());

            if (objCadastrar.getFederacao() != 0)
                pessoaCompetitiva.setFederacao(objCadastrar.getFederacao());

            // Salvando a Pessoa Competitiva
            this.pessoaCompetitivaRepository.save(pessoaCompetitiva);

            return pessoaCompetitiva.getId();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return -1;
        }
    }

}