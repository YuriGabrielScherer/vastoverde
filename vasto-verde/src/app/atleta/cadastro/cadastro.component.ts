import { HttpClient } from '@angular/common/http';
import { Router, Route } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ValidacoesFormService } from './../../shared/services/validacoes-form.service';
import { Pessoa } from '../../shared/model/pessoa';
import { PessoaService } from '../pessoa.service';
import { ToastService } from './../../shared/services/toast/toast.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss'],
  preserveWhitespaces: true
})
export class CadastroComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private pessoaService: PessoaService,
    private validacaoForm: ValidacoesFormService,
    private toastService: ToastService,
    private router: Router,
    private route: Route
  ) {

    // Propriedades do Input de Data de Nascimento
    this.maxDate = new Date();
    this.maxDate.setDate(this.maxDate.getDate());
  }

  // Variavel do Formulario para controle
  formulario: FormGroup;

  // Setando variavel de Data Maxima
  maxDate: Date;

  // Mascaras para os campos Input
  public maskTelefone = ['(', /[1-9]/, /\d/, ')', /\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
  public maskCpf = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];

  // Variavel validar Tela Alteracao
  telaAlteracao = false;

  // Pessoa a ser cadastrada
  objetoPessoa: Pessoa = new Pessoa();

  ngOnInit() {

    // Tentando pegar atributos do Roteamento
    console.log(this.route);

    // Atribuindo o formulario para a variavel
    this.formulario = this.formBuilder.group({
      // Campos previstos
      nome: [null,  // Valor inicial nulo
        Validators.required], // Validacoes dos campos
      cpf: [null, [Validators.required, this.validacaoForm.isValidCpf()]],
      email: [null, [Validators.required, Validators.email]],
      telefone: [null, [Validators.required, this.validacaoForm.isValidPhone()]],
      dataNascimento: [null, [Validators.required]],
      senha: [null, [Validators.required, Validators.minLength(5)]]
    });

  }

  // OnSubmit do Formulario
  private onSubmit() {

    // Verificando se o formulario é valido
    if (this.formulario.valid) {
      // Service de POST
      this.pessoaService.save(this.criarObjetoPessoa(this.formulario))
        .subscribe((retorno) => {
          // Mensagem Sucesso
          this.toastService.toastSuccess(this.formulario.get('nome').value + ', cadastrado com sucesso!', 'Cadastro Realizado!');
          // Resetando o Form
          this.formulario.reset();
          // Navegando para a tela de Login
          if (sessionStorage.getItem('usuario_logado') === null) {
            this.router.navigate(['/login']);
          } else {
            this.router.navigate(['/administrativo']);
          }
        },
          (error) => {
            console.log(error);
          });
    } else {
      // Resgatando os Componentes do Formulario
      this.validaFormulario(this.formulario);
    }

  }

  // Criando objeto Pessoa
  private criarObjetoPessoa(formGroup: FormGroup): Pessoa {

    // Atribuindo valores
    this.objetoPessoa.nome = formGroup.get('nome').value;
    this.objetoPessoa.cpf = formGroup.get('cpf').value;
    this.objetoPessoa.email = formGroup.get('email').value;
    this.objetoPessoa.senha = formGroup.get('senha').value;
    this.objetoPessoa.telefone = formGroup.get('telefone').value;

    // Tratando Data de Nascimento
    const data: Date = formGroup.get('dataNascimento').value;
    this.objetoPessoa.dataNascimento = data.getDate() + '/' + data.getMonth() + '/' + data.getFullYear();

    // Retornando objeto para POST
    return this.objetoPessoa;
  }

  // Realizando verificacao Campo por Campo
  private validaFormulario(grupo: FormGroup) {
    // This.Formulario.Controls retorna o objeto que nao pode ser lido aqui.
    // Assim, atribui-se Chaves para o Objeto poder ser lido.

    Object.keys(this.formulario.controls).forEach(controle => {
      // Marcando como Touched para aplicar as validacoes
      this.formulario.get(controle).markAsTouched();
    });
  }

  // Resetando o Formulario
  private resetarForm() {
    this.formulario.reset();
  }

  // Validacoes de Erros
  private aplicaCss(campo) {
    return {
      'is-invalid': this.verificaValidTouched(campo),
      'is-valid': !this.verificaValidTouched(campo) && (this.formulario.get(campo).touched),
    };
  }

  // Validacao de Erro do Campo CPF
  private aplicaCssCpf() {

    // Var Auxiliar
    let digitos = '';

    // Retirando mascara
    if (this.formulario.get('cpf').value) {
      digitos = (this.formulario.get('cpf').value).replace(/[^0-9]+/g, '');
    }

    // Verificando tamanho do CPF
    if (digitos.length === 11) {

      // Verificando Valido
      return {
        'is-valid': this.formulario.get('cpf').errors == null,
        'is-invalid': this.formulario.get('cpf').errors
      };
    } else {
      return {
        'is-invalid': this.formulario.get('cpf').touched
      };
    }
  }

  // Verificando se o campo está invalido e se foi Focado
  private verificaValidTouched(campo) {
    return !this.formulario.get(campo).valid && this.formulario.get(campo).touched;
  }
}
