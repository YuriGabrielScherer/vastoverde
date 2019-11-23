import { Atleta } from './../../shared/model/atleta';
import { AtletaService } from './../atleta.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss'],
  preserveWhitespaces: true
})
export class CadastroComponent implements OnInit {

  // Variavel do Formulario para controle
  formulario: FormGroup;

  // Mascaras para os campos Input
  public maskTelefone = ['(', /[1-9]/, /\d/, ')', ' ', /\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
  public maskCpf = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];

  // Variavel validar Tela Alteracao
  protected telaAlteracao = false;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private atletaService: AtletaService
  ) { }

  ngOnInit() {

    // Atribuindo o formulario para a variavel
    this.formulario = this.formBuilder.group({
      // Campos previstos
      nome: [null,  // Valor inicial nulo
        Validators.required], // Validacoes dos campos
      cpf: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      telefone: [null, [Validators.required]],
      dataNascimento: [null, [Validators.required]],
      senha: [null, [Validators.required, Validators.minLength(5)]]
    });
  }

  // OnSubmit do Formulario
  onSubmit() {
    console.log(this.formulario);


  }

  // Resetando o Formulario
  resetarForm() {
    this.formulario.reset();
  }

  // Validacoes de Erros
  aplicaCssErro(campo) {
    return {
      // 'has-error': this.verificaValidTouched(campo),
      'invalid-feedback': this.verificaValidTouched(campo),
    };
  }

  verificaValidTouched(campo) {
    // Verificando se o campo está invalido e se foi Focado
    return !this.formulario.get(campo).valid && this.formulario.get(campo).touched;
  }


}
