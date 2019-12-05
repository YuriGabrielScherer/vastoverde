package br.com.karate.projetokarate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import br.com.karate.projetokarate.model.RespostaModelo;
import br.com.karate.projetokarate.repository.PessoaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Metodo Cadastrar
    @RequestMapping(value = "/pessoa", method = RequestMethod.POST)
    public @ResponseBody RespostaModelo save(@RequestBody PessoaModelo pessoa) {
        try {
            this.pessoaRepository.save(pessoa);
            return new RespostaModelo(pessoa.getNomePessoa() + " foi salvo (a) com sucesso!", "Sucesso!");
        } catch (Exception erro) {
            return new RespostaModelo(erro.getMessage());
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

    // Metoto Listar por Email
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

    // Metodo Excluir
    @RequestMapping(value = "/pessoa/{idPessoa}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody RespostaModelo delete(@PathVariable("idPessoa") int idPessoa) {

        // Buscando a pessoa no Banco com o codigo
        PessoaModelo pessoa = this.pessoaRepository.findById(idPessoa);

        // Excluindo
        try {
            this.pessoaRepository.delete(pessoa);
            return new RespostaModelo("Registro excluído com sucesso!", "Sucesso!");
        } catch (Exception erro) {
            return new RespostaModelo(erro.getMessage());
        }
    }

    // Metodo Realizar login
    @RequestMapping(value = "/pessoa/login", method = RequestMethod.POST)
    public ResponseEntity<PessoaModelo> auth(@RequestBody PessoaLogin pessoaLogin) {

        try {
            // Retornando as pessoas
            PessoaModelo pessoa = this.buscarPorEmail(pessoaLogin.getLogin());

            // Verificando a senha
            if (pessoa.getSenhaPessoa().equals(pessoaLogin.getSenha())) {

                return new ResponseEntity<PessoaModelo>(pessoa, HttpStatus.OK);
            }

            // Caso Senha errada.
            return null;
        } catch (Exception erro) {
            System.out.println(erro.getStackTrace());
        }
        return null;
    }
}