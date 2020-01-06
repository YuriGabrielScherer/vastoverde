package br.com.karate.projetokarate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.model.AtletaModelo;
import br.com.karate.projetokarate.model.AtletaPessoaCompetitivaModelo;
import br.com.karate.projetokarate.repository.AtletaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AtletaService {

    @Autowired
    private AtletaRepository atletaRepository;
    @Autowired
    private PessoaCompetitivaService pessoaCompetitivaService;

    // Metodo Cadastrar
    @RequestMapping(value = "/atleta", method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody AtletaPessoaCompetitivaModelo objCadastrar) {

        // Cadastrando a Pessoa Competitiva
        int idPessoaCompetitiva = this.pessoaCompetitivaService.savePessoaCompetitiva(objCadastrar);
        if (idPessoaCompetitiva >= 0) {
            // Se a Pessoa Competitiva foi Cadastrada, retornara um ID > 0
            // Atribuindo o ID da PessoaCompetitiva
            objCadastrar.setIdPessoaCompetitiva(idPessoaCompetitiva);

            try {
                AtletaModelo atleta = criarObjetoAtleta(objCadastrar);

                // Tentando Salvar Atleta
                atletaRepository.save(atleta);

                // Retorno
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                System.out.println(e.getMessage());

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Metodo para criar um objeto atleta
    private AtletaModelo criarObjetoAtleta(AtletaPessoaCompetitivaModelo objeto) {
        // Objeto Atleta
        AtletaModelo atleta = new AtletaModelo();

        try {
            // Passando as informacoes
            atleta.setIdPessoaCompetitiva(objeto.getIdPessoaCompetitiva());
            atleta.setNomeResponsavel(objeto.getNomeResponsavel());
            atleta.setTelefoneResponsavel(objeto.getTelefoneResponsavel());
            atleta.setCpfResponsavel(objeto.getCpfResponsavel());

            // Retornando para salvar
            return atleta;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

}