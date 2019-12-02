import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { AuthService } from './../auth.service';

// import { ToastrService } from 'ngx-toastr';

import { ToastService } from '../../shared/services/toast/toast.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private login = {
    email: null,
    senha: null,
    lembrar: false
  };

  //  Variavel de controle
  spinnerCarregar = false;

  // Formulario de Login
  formulario: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,

    // private toast: ToastrService,
    private toastService: ToastService,

    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    // Criando o formulario
    this.formulario = this.formBuilder.group({
      email: [null, Validators.required],
      senha: [null, Validators.required],
      lembrar: [false]
    });

  }

  // Metodo que realiza o Login
  realizarLogin() {

    console.log(this.authService.usuarioAutenticado());


    // Colocando o spinner
    this.spinnerCarregar = true;

    // Validando formulário
    if (this.formulario.valid) {

      // Realizando o login
      this.authService.realizarLogin(this.criarObjeto());

      setTimeout(() => {

        // Verificando
        if (this.authService.usuarioAutenticado()) {
          // Toast
          this.toastService.toastSuccess('Usuário - Yuri Gabriel!', 'Bem-vindo ao Sistema Vasto Verde!');

          // Escondendo Spinner
          this.spinnerCarregar = false;

          // Resetando o formulario
          this.formulario.reset();

          // Rotacionando
          this.router.navigate(['/administrativo']);
        } else {

          // Toast
          this.toastService.toastWarning('Erro ao realizar o login.',
            'Por favor, confira se o usuário e senha estão corretos e tente novamente.');

          // Escondendo Spinner
          this.spinnerCarregar = false;

          // Selecionando o Campo de nome.
          const campoNome = document.getElementById('campoEmail') as HTMLInputElement;
          campoNome.focus();
        }
      },
        2500);
    }

  }

  // Metodo para popular o Objeto de login
  criarObjeto() {
    // Atribuindo valores
    this.login.email = this.formulario.get('email').value;
    this.login.senha = this.formulario.get('senha').value;
    this.login.lembrar = this.formulario.get('lembrar').value;

    return this.login;
  }

}
