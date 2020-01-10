package br.com.karate.projetokarate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.model.AtletaModelo;
import br.com.karate.projetokarate.model.AtletaPessoaCompetitivaModelo;
import br.com.karate.projetokarate.model.VwPessoaAtletaModelo;
import br.com.karate.projetokarate.model.VwPessoaGrauModelo;
import br.com.karate.projetokarate.repository.AtletaRepository;
import br.com.karate.projetokarate.repository.VwPessoaAtletaRepository;
import br.com.karate.projetokarate.repository.VwPessoaGrauRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AtletaService {

    @Autowired
    private AtletaRepository atletaRepository;
    @Autowired
    private PessoaCompetitivaService pessoaCompetitivaService;
    @Autowired
    private VwPessoaAtletaRepository vwPessoaAtletaRepository;
    @Autowired
    private VwPessoaGrauRepository vwPessoaGrauRepository;

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

    // Metodo para listar o Atleta-PessoaCompetitiva-Pessoa -> Atraves de View
    // Listar todos
    @RequestMapping(value = "/atleta", method = RequestMethod.GET)
    public @ResponseBody List<VwPessoaGrauModelo> getAll() {
        // Retornando
        return vwPessoaGrauRepository.findAll();
    }

    // Listar por Codigo de ATLETA
    @RequestMapping(value = "/atleta/{idAtleta}", method = RequestMethod.GET)
    public ResponseEntity<VwPessoaAtletaModelo> getByid(@PathVariable("idAtleta") int idAtleta) {

        // Objeto que vai ser retornado
        VwPessoaAtletaModelo pessoaAtleta = null;

        // Retornando toda a Lista Atualizada
        List<VwPessoaAtletaModelo> vwPessoas = this.vwPessoaAtletaRepository.findAll();

        // Filtrando pelo ID para retornar para o usuario
        for (int i = 0; i < vwPessoas.size(); i++) {
            System.out.println(vwPessoas.get(i).getIdAtleta() + " -- " + i);
            if (vwPessoas.get(i).getIdAtleta() == idAtleta) {
                pessoaAtleta = vwPessoas.get(i);
                return new ResponseEntity<>(pessoaAtleta, HttpStatus.OK);
            }
        }

        // Caso nao encontrado
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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