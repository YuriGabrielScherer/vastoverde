package br.com.karate.projetokarate.atleta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.karate.projetokarate.pessoa.Pessoa;
import br.com.karate.projetokarate.pessoa.PessoaRepository;
import br.com.karate.projetokarate.service.email.NomeEmailModelo;

import java.util.ArrayList;

// Desktop - Acesso ao Site
// import java.awt.*;
// import java.io.IOException;
// import java.net.URI;
// import java.net.URISyntaxException;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/atleta")
public class AtletaService {

    @Autowired
    private AtletaRepository atletaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

//    // Metodo Cadastrar
//    @RequestMapping(value = "/atleta", method = RequestMethod.POST)
//    public ResponseEntity<String> save(@RequestBody Atleta objCadastrar) {
//
//        // Cadastrando a Pessoa Competitiva
//        int idPessoaCompetitiva = this.AtletaRepository.save(objCadastrar);
//        if (idPessoaCompetitiva >= 0) {
//            // Se a Pessoa Competitiva foi Cadastrada, retornara um ID > 0
//            // Atribuindo o ID da PessoaCompetitiva
//            objCadastrar.setIdPessoaCompetitiva(idPessoaCompetitiva);
//
//            try {
//                Atleta atleta = criarObjetoAtleta(objCadastrar);
//
//                // Tentando Salvar Atleta
//                atletaRepository.save(atleta);
//
//                // Retorno
//                return new ResponseEntity<>(HttpStatus.OK);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    // Metodo para listar o Atleta-PessoaCompetitiva-Pessoa -> Atraves de View
    // Listar todos
//    @GetMapping
//    public @ResponseBody List<VwPessoaGrauModelo> getAll() {
//        // Retornando
//        return vwPessoaGrauRepository.findAll();
//    }

    // Listar por Codigo de ATLETA
//    @GetMapping("/{idAtleta}")
//    public ResponseEntity<VwPessoaAtletaModelo> getByid(@PathVariable("idAtleta") int idAtleta) {
//
//        // Objeto que vai ser retornado
//        VwPessoaAtletaModelo pessoaAtleta = null;
//
//        // Retornando toda a Lista Atualizada
//        List<VwPessoaAtletaModelo> vwPessoas = this.vwPessoaAtletaRepository.findAll();
//        try {
//
//            // Filtrando pelo ID para retornar para o usuario
//            for (int i = 0; i < vwPessoas.size(); i++) {
//                if (vwPessoas.get(i).getIdAtleta() == idAtleta) {
//                    pessoaAtleta = vwPessoas.get(i);
//                    return new ResponseEntity<>(pessoaAtleta, HttpStatus.OK);
//                }
//            }
//
//            // Caso nao encontrado
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Excluir Atleta - Pessoa Competitiva Tambem
//    @RequestMapping(value = "/atleta/{idAtleta}", method = RequestMethod.DELETE)
//    public ResponseEntity<String> delete(@PathVariable("idAtleta") int idAtleta) {
//
//        try {
//
//            // Procurando atleta para saber o Id Pessoa Competitiva.
//            Atleta atleta = this.atletaRepository.findById(idAtleta);
//
//            int idPessoaCompetitiva = atleta.getIdPessoaCompetitiva();
//
//            // Excluindo atleta
//            this.atletaRepository.delete(atleta);
//
//            // Excluindo Pessoa Competitiva
//            this.pessoaCompetitivaService.excluir(idPessoaCompetitiva);
//
//            return new ResponseEntity<>(HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//    }

    // Metodo para Retonar todos os nomes e emails dos usuarios
//    @RequestMapping(value = "/atleta/nomeEmails", method = RequestMethod.GET)
//    public ResponseEntity<List<NomeEmailModelo>> requestMethodName() {
//
//        // Variavel para ser retornada
//        List<NomeEmailModelo> destinatarios = new ArrayList<NomeEmailModelo>();
//
//        try {
//
//            // Pessoas
//            List<PessoaModelo> pessoas = pessoaRepository.findAll();
//
//            for (int i = 0; i < pessoas.size(); i++) {
//                destinatarios.add(new NomeEmailModelo(pessoas.get(i).getidPessoa(), pessoas.get(i).getNomePessoa(),
//                        pessoas.get(i).getEmailPessoa()));
//            }
//
//            return new ResponseEntity<List<NomeEmailModelo>>(destinatarios, HttpStatus.OK);
//        } catch (Exception e) {
//            System.out.println(e.getStackTrace());
//            System.out.println(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    // // Metodo para abrir o Link
    // @RequestMapping(value = "/fck", method = RequestMethod.GET)
    // public ResponseEntity<String> cadastrarFck() {

    // navegarFck("http://www.fck.g2e.com.br/");
    // return new ResponseEntity<String>("Abriu", HttpStatus.OK);

    // }

    // Metodo para criar um objeto atleta
//    private Atleta criarObjetoAtleta(AtletaPessoaCompetitivaModelo objeto) {
//        // Objeto Atleta
//        Atleta atleta = new Atleta();
//
//        try {
//            // Passando as informacoes
//            atleta.setId(objeto.getIdAtleta());
//            atleta.setIdPessoaCompetitiva(objeto.getIdPessoaCompetitiva());
//            atleta.setNomeResponsavel(objeto.getNomeResponsavel());
//            atleta.setTelefoneResponsavel(objeto.getTelefoneResponsavel());
//            atleta.setCpfResponsavel(objeto.getCpfResponsavel());
//
//            // Retornando para salvar
//            return atleta;
//        } catch (Exception e) {
//            System.out.println(e.getStackTrace());
//            return null;
//        }
//    }
}