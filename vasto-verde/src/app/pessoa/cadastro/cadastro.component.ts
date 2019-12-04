import { Subscription } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
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


  // Variavel do Formulario para controle
  formulario: FormGroup;

  // Setando variavel de Data Maxima
  maxDate: Date;

  // Mascaras para os campos Input
  public maskTelefone = ['(', /[1-9]/, /\d/, ')', /\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
  public maskCpf = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];

  // Variavel validar Tela Alteracao
  telaAlteracao = false;
  // Variavel inscricao para tela de Alteracao
  private inscricao: Subscription;

  // Pessoa a ser cadastrada
  objetoPessoa: Pessoa = new Pessoa();


  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private pessoaService: PessoaService,
    private validacaoForm: ValidacoesFormService,
    private toastService: ToastService,
    private router: Router,
    private route: ActivatedRoute
  ) {

    // Propriedades do Input de Data de Nascimento
    this.maxDate = new Date();
    this.maxDate.setDate(this.maxDate.getDate());
  }


  ngOnInit() {

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

    // Tentando pegar atributos do Roteamento
    this.inscricao = this.route.data.subscribe((dados: Pessoa) => {

      // Verificando se tem Pessoa para alterar
      if (dados['alteracao']) {

        // Validando toda a tela de Alteracao
        this.telaAlteracao = true;

        // Pegando os dados
        this.objetoPessoa = dados['alteracao'];

        // Atribuindo valores para o formulario
        this.formulario.patchValue({
          nome: this.objetoPessoa.nomePessoa,
          cpf: this.objetoPessoa.cpfPessoa,
          email: this.objetoPessoa.emailPessoa,
          telefone: this.objetoPessoa.telefonePessoa,
          dataNascimento: this.objetoPessoa.dataNascimentoPessoa,
          senha: this.objetoPessoa.senhaPessoa
        });

        // Setando Formulario como valido - Aplicar CSS Valido
        this.formulario.markAllAsTouched();
      }
    });

  }

  ngOnDestroy() {
    this.inscricao.unsubscribe();
  }

  // OnSubmit do Formulario
  private onSubmit() {

    // Verificando se o formulario é valido
    if (this.formulario.valid && this.formulario.dirty) {

      // Service de POST
      this.pessoaService.save(this.criarObjetoPessoa(this.formulario))
        .subscribe((retorno) => {

          // Mensagem Sucesso
          this.toastService.toastSuccess(retorno['mensagem'], retorno['titulo']);

          // Resetando o Form
          this.formulario.reset();

          // Navegando para a tela de Login
          if ((sessionStorage.getItem('usuario_logado') === null) &&
            (localStorage.getItem('usuario_logado') === null)) {
            this.router.navigate(['/login']);
          } else {
            this.router.navigate(['/administrativo']);
          }
        },
          (error) => {
            this.toastService.toastError('Erro ao se conectar com o banco de dados.',
              'Se o problema persistir, contate o administrador do sistema.');
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
    this.objetoPessoa.nomePessoa = formGroup.get('nome').value;
    this.objetoPessoa.emailPessoa = formGroup.get('email').value;
    this.objetoPessoa.senhaPessoa = formGroup.get('senha').value;
    this.objetoPessoa.telefonePessoa = formGroup.get('telefone').value;

    // Tratando o CPF - Retirando a mascara
    const cpf: string = formGroup.get('cpf').value.replace(/[^0-9]+/g, '');
    this.objetoPessoa.cpfPessoa = cpf;

    // Tratando Data de Nascimento
    const data: Date = formGroup.get('dataNascimento').value;
    this.objetoPessoa.dataNascimentoPessoa = data.getDate() + '/' + data.getMonth() + '/' + data.getFullYear();

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

  resetForm() {

    // Validando Modo Alteracao
    if (this.telaAlteracao) {
      this.router.navigate(['/administrativo']);
    } else {

      this.formulario.reset();

      Object.keys(this.formulario.controls).forEach(controle => {
        // Marcando como Touched para aplicar as validacoes
        this.formulario.get(controle).markAsUntouched();
      });
    }
  }
}
