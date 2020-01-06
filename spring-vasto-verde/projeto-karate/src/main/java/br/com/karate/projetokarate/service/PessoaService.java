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

import br.com.karate.projetokarate.model.PessoaLogin;
import br.com.karate.projetokarate.model.PessoaModelo;
import br.com.karate.projetokarate.repository.PessoaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PessoaService {

  @Autowired
  private PessoaRepository pessoaRepository;

  // Metodo Cadastrar
  @RequestMapping(value = "/pessoa", method = RequestMethod.POST)
  public ResponseEntity<PessoaModelo> save(@RequestBody PessoaModelo pessoa) {
    try {
      this.pessoaRepository.save(pessoa);
      return new ResponseEntity<PessoaModelo>(pessoa, HttpStatus.OK);
    } catch (Exception erro) {
      return new ResponseEntity<PessoaModelo>(pessoa, HttpStatus.BAD_REQUEST);
    }
  }

  // Metodo Listar Todos
  @RequestMapping(value = "/pessoa", method = RequestMethod.GET)
  public @ResponseBody List<PessoaModelo> getAll() {
    // Retornando Lista
    return this.pessoaRepository.findAll();
  }

  // Metodo Listar por Codigo
  @RequestMapping(value = "/pessoa/{idPessoa}", method = RequestMethod.GET)
  public @ResponseBody PessoaModelo getById(@PathVariable("idPessoa") int idPessoa) {
    return this.pessoaRepository.findById(idPessoa);
  }

  // Metodo Get por Email
  @RequestMapping(value = "/pessoa/email/{emailPessoa}", method = RequestMethod.GET)
  public @ResponseBody PessoaModelo buscarPorEmail(@PathVariable("emailPessoa") String emailPessoa) {

    // Criando Objeto para retornar
    PessoaModelo pessoa = new PessoaModelo();

    // Realizando busca por todas as pessoas
    List<PessoaModelo> pessoas = this.pessoaRepository.findAll();

    // Realizando a Verificacao
    for (int i = 0; i < pessoas.size(); i++) {
      if (pessoas.get(i).getEmailPessoa().equals(emailPessoa)) {
        pessoa = pessoas.get(i);
        break;
      }
    }

    // Retornando
    return pessoa;
  }

  // Metodo Get por CPF
  @RequestMapping(value = "/pessoa/cpf/{cpfPessoa}", method = RequestMethod.GET)
  public ResponseEntity<PessoaModelo> buscarPorCpf(@PathVariable("cpfPessoa") String cpfPessoa) {

    try {
      // Criando Objeto Pessoa
      PessoaModelo pessoa = new PessoaModelo();

      // Retornando todas as pessoas
      List<PessoaModelo> pessoas = this.pessoaRepository.findAll();

      for (int i = 0; i < pessoas.size(); i++) {
        if (pessoas.get(i).getCpfPessoa().equals(cpfPessoa)) {
          // Atribuindo valor e retornando
          pessoa = pessoas.get(i);
          return new ResponseEntity<PessoaModelo>(pessoa, HttpStatus.OK);
        }
      }

      // Caso nao encontrado.
      return new ResponseEntity<PessoaModelo>(new PessoaModelo(), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      // Retorno de erro.
      System.out.println(e.getStackTrace());
      return new ResponseEntity<PessoaModelo>(new PessoaModelo(), HttpStatus.BAD_REQUEST);
    }

  }

  // Metodo Excluir
  @RequestMapping(value = "/pessoa/{idPessoa}", method = RequestMethod.DELETE)
  public ResponseEntity<String> delete(@PathVariable("idPessoa") int idPessoa) {

    // Excluindo
    try {
      // Buscando a pessoa no Banco com o codigo
      PessoaModelo pessoa = this.pessoaRepository.findById(idPessoa);

      // Verificando Pessoa Cadastrada
      if (pessoa == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      // Excluindo e retornando 
      this.pessoaRepository.delete(pessoa);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception erro) {
      return new ResponseEntity<>(erro.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  // Metodo Realizar login
  @RequestMapping(value = "/pessoa/login", method = RequestMethod.POST)
  public ResponseEntity<PessoaModelo> login(@RequestBody PessoaLogin pessoaLogin) {

    try {
      // Retornando as pessoas
      PessoaModelo pessoa = this.buscarPorEmail(pessoaLogin.getLogin());

      // Verificando a senha
      if (pessoa.getSenhaPessoa().equals(pessoaLogin.getSenha())) {
        return new ResponseEntity<PessoaModelo>(pessoa, HttpStatus.OK);
      }

      // Caso Senha errada.
      return new ResponseEntity<PessoaModelo>(new PessoaModelo(), HttpStatus.NOT_FOUND);
    } catch (Exception erro) {
      System.out.println(erro.getStackTrace());
      return new ResponseEntity<PessoaModelo>(new PessoaModelo(), HttpStatus.BAD_REQUEST);
    }
  }

}