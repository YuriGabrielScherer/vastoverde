import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from './../auth.service';

import { Pessoa } from './../../shared/model/pessoa';
import { PessoaService } from './../../pessoa/pessoa.service';

import { ToastService } from '../../shared/services/toast/toast.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private login = {
    login: null,
    senha: null
  };

  private lembrarDeMim = false;

  //  Variavel de controle
  spinnerCarregar = false;

  // Formulario de Login
  formulario: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,
    private pessoaService: PessoaService,
    private toastService: ToastService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.formulario = this.formBuilder.group({
      email: [null, Validators.required],
      senha: [null, Validators.required],
      lembrar: [false]
    });

  }

  private realizarLogin() {

    if (this.formulario.valid) {

      this.spinnerCarregar = true;

      // Criando objeto
      this.criarObjeto();

      // Realizando login
      this.pessoaService.login(this.login).subscribe(

        // Request Success
        (pessoaLogada: Pessoa) => {

          // Resetando o formulario
          this.formulario.reset();

          // Mensagem
          this.toastService.toastSuccess('Login realizado com sucesso.', 'Bem-vindo ao sistema!');

          // Salvando login na memoria
          this.lembrarDeMim ?
            localStorage.setItem('usuario_logado', pessoaLogada.idPessoa.toString())
            : sessionStorage.setItem('usuario_logado', pessoaLogada.idPessoa.toString());

          // sessionStorage.setItem('usuario_logado', pessoaLogada.idPessoa.toString());

          // Setando a pessoa logada.
          this.authService.setUsuarioLogado(pessoaLogada);

          // Rotacionando
          this.router.navigate(['/administrativo']);
        },
        // Tratativa de Erros
        (error) => {

          // Verificando falha com o banco ou Login
          if (error['status'] === 404) {
            // Toast
            this.toastService.toastWarning('Erro ao realizar o login.',
              'Por favor, confira se o usuário e senha estão corretos e tente novamente.');

            // Selecionando o Campo de nome.
            const campoNome = document.getElementById('campoEmail') as HTMLInputElement;
            campoNome.focus();
          } else {
            // Toast
            this.toastService.toastErroBanco();
          }
        });
      this.spinnerCarregar = !this.spinnerCarregar;
    }

  }
  // Metodo para popular o Objeto de login
  criarObjeto() {
    // Atribuindo valores ao objeto logins
    this.login.login = this.formulario.get('email').value.toString();
    this.login.senha = this.formulario.get('senha').value.toString();

    this.lembrarDeMim = this.formulario.get('lembrar').value;
  }

}
