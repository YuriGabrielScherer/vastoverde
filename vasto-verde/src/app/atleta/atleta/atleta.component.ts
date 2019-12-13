import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { Atleta } from './../../shared/model/atleta';
import { Pessoa } from './../../shared/model/pessoa';
import { PessoaService } from './../../pessoa/pessoa.service';
import { ToastService } from './../../shared/services/toast/toast.service';
import { ValidacoesFormService } from './../../shared/services/validacoes-form.service';

@Component({
  selector: 'app-atleta',
  templateUrl: './atleta.component.html',
  styleUrls: ['./atleta.component.scss'],
  preserveWhitespaces: true
})
export class AtletaComponent implements OnInit {

  // Mascara para o CPF
  public maskCpf = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];
  public maskData = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/];
  public maskTel = ['(', /[1-9]/, /\d/, ')', /\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];

  // Formulario
  formulario: FormGroup;

  // Objetos
  atleta: Atleta = null;
  pessoa: Pessoa = null;

  // Var Controle Spinner
  spinnerCarregar = false;

  constructor(
    private validacoesForm: ValidacoesFormService,
    private formBuild: FormBuilder,
    private pessoaService: PessoaService,
    private toast: ToastService
  ) { }

  ngOnInit() {
    // Criando formulario
    this.formulario = this.formBuild.group({
      // Dados da Pessoa
      idPessoa: [null, Validators.required],
      cpfAtleta: [null, [Validators.required, this.validacoesForm.isValidCpf()]],
      // Dados do Atleta
      nomeResp: [null, Validators.required],
      cpfResp: [null, [Validators.required, this.validacoesForm.isValidCpf()]],
      telResp: [null, [Validators.required, this.validacoesForm.isValidPhone()]],
      dataInicio: [null, Validators.required],
      grau: [null, Validators.required],
      federacao: [null],
      confederacao: [null]
    });
  }

  // Retorna dados do Atleta do Cadastro Pessoa
  buscarDadosAtleta() {

    // Pegando valor do Componente
    if (this.formulario.get('cpfAtleta').valid) {

      // Spinner
      this.spinnerCarregar = true;

      // Retirando mascara
      const cpf: string = this.formulario.get('cpfAtleta').value.replace(/[^0-9]+/g, '');

      this.pessoaService.loadbyCpf(cpf).subscribe(
        // Caso Sucesso
        (response: Pessoa) => {
          // Passando dados
          this.pessoa = response;

          // Bloqueando Campo CPF
          this.formulario.get('cpfAtleta').disable();
        },
        // Tratamento de erros
        (error) => {
          if (error['status'] === 404) {
            // CPF nao cadastrado
            this.toast.toastWarning('CPF não encontrado.',
              'Confira o CPF ou realize o cadastro da pessoa.');
            this.formulario.get('cpfAtleta').setErrors(isNaN);
          } else {
            this.toast.toastErroBanco();
          }
        },
        // Quando o Request terminar, tirar Spinner.
        () => this.spinnerCarregar = false);

    }
  }

  // Resetando o formulario inteiro
  cancelarCadastro() {
    // Controle de Usuario Selecionado
    this.pessoa = null;
    // Resetando formulario
    this.formulario.reset();
    // Habilitando Campo CPF
    this.formulario.get('cpfAtleta').enable();
  }
}
