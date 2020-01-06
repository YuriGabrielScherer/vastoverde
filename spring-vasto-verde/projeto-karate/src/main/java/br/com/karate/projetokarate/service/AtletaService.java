package br.com.karate.projetokarate.service;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Any;

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
import br.com.karate.projetokarate.model.PessoaCompetitivaModelo;
import br.com.karate.projetokarate.repository.AtletaRepository;
import br.com.karate.projetokarate.repository.PessoaCompetitivaRepository;

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
    public ResponseEntity<AtletaPessoaCompetitivaModelo> save(@RequestBody AtletaPessoaCompetitivaModelo objCadastrar) {

        // Cadastrando a Pessoa Competitiva
        if (this.pessoaCompetitivaService.savePessoaCompetitiva(objCadastrar) >= 0) {
            // Se a Pessoa Competitiva foi Cadastrada,
            // Cadastra o Atleta
            System.out.println("Deu certo");
        } else {
            System.out.println("Deu ERRADO");

        }

        // try{
        // this.atletaRepository.save(atleta);
        return new ResponseEntity<AtletaPessoaCompetitivaModelo>(objCadastrar, HttpStatus.OK);
        // } catch(Exception erro){
        // return new ResponseEntity<AtletaModelo>(new AtletaModelo(),
        // HttpStatus.BAD_REQUEST);
        // }

    }

    // // Metodo para criar um objeto atleta
    // private AtletaModelo criarObjeto(AtletaPessoaCompetitivaModelo objCadastrar)
    // {
    // // Objeto
    // AtletaModelo atleta = new AtletaModelo();

    // try {
    // atleta.setIdPessoaCompetitiva(objCadastrar.getIdPessoaCompetitiva()
    // } catch (Exception e) {
    // System.out.println(e.getStackTrace());
    // return null;
    // }
    // }

}