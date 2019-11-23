import { Atleta } from './../../shared/model/atleta';
import { AtletaService } from './../atleta.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ValidationErrors } from '@angular/forms';

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

  // Atleta a ser cadastrado
  objetoAtleta: Atleta = new Atleta();

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
  private onSubmit() {
    console.log(this.formulario.value);

    // Verificando se o formulario é valido
    if (this.formulario.valid) {
      // Service de POST
      this.atletaService.save(this.formulario.value)
        .subscribe((retorno) => {
          console.log(retorno);
        },
        (error) => {
          console.log(error);
        });
    } else {
      // Resgatando os Componentes do Formulario
      this.validaFormulario(this.formulario);
    }

  }

  // Criando objeto Atleta
  private criarObjetoAtleta(formGroup: FormGroup): Atleta {

    // Atribuindo valores
    this.objetoAtleta.nome = formGroup.get('nome').value;
    this.objetoAtleta.cpf = formGroup.get('cpf').value;
    this.objetoAtleta.dataNascimento = formGroup.get('dataNascimento').value;
    this.objetoAtleta.email = formGroup.get('email').value;
    this.objetoAtleta.senha = formGroup.get('senha').value;

    // Retornando objeto para POST
    return this.objetoAtleta;
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

  // Verificando se o campo está invalido e se foi Focado
  private verificaValidTouched(campo) {
    return !this.formulario.get(campo).valid && this.formulario.get(campo).touched;
  }

  // Verifica tamanho da senha.
  // verificaTamanhoSenha() {

  //   // Variavel para retornar o erro
  //   const controlErrors: ValidationErrors = this.formulario.get('senha').errors;
  //   if (controlErrors != null) {
  //     Object.keys(controlErrors).forEach(keyError => {
  //       console.log('Key control: senha, keyError: ' + keyError + ', err value: ', keyError);
  //       let a = controlErrors[keyError];
  //       return a['requiredLength'];
  //     });
  //   }
  // }


}
