import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

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
  public maskCpf = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/  ];

  // Variavel validar Tela Alteracao
  protected telaAlteracao = false;

  constructor(
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    // Atribuindo o formulario para a variavel
    this.formulario = this.formBuilder.group({
      // Campos previstos
      nome: [null], // Valor inicial nulo
      cpf: [null],
      email: [null],
      telefone: [null],
      dataNascimento: [null],
      senha: [null]
    });
  }

}
