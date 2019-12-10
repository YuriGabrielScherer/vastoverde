import { PessoaService } from './../../pessoa/pessoa.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { ValidacoesFormService } from './../../shared/services/validacoes-form.service';
import { Pessoa } from './../../shared/model/pessoa';
import { Atleta } from './../../shared/model/atleta';

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

  // Objeto Atleta
  atleta: Atleta = null;
  pessoa: Pessoa = null;

  constructor(
    private validacoesForm: ValidacoesFormService,
    private formBuild: FormBuilder,
    private pessoaService: PessoaService
  ) { }

  ngOnInit() {
    // Criando formulario
    this.formulario = this.formBuild.group({
      idPessoa: [null, Validators.required],
      cpfAtleta: [null, [Validators.required, this.validacoesForm.isValidCpf()]],
      nomeResp: [null, Validators.required],
      cpfResp: [null, [Validators.required, this.validacoesForm.isValidCpf()]],
      telResp: [null, [Validators.required, this.validacoesForm.isValidPhone()]],
      dataInicio: [null, Validators.required],
      grau: [null, Validators.required],
      federacao: [null],
      confederacao: [null]
    });
  }

  buscarDadosAtleta() {

    // Pegando valor do Componente
    if (this.formulario.get('cpfAtleta').valid) {
      // this.pessoaService.
    }
  }
}
