import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { AuthService } from './../auth.service';

// import { ToastrService } from 'ngx-toastr';

import { ToastService } from './../../shared/toast/toast.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  email = '';
  senha = '';

  //  Variavel de controle
  spinnerCarregar = false;

  // Options para o Toast

  constructor(
    private authService: AuthService,
    private router: Router,

    // private toast: ToastrService,
    private toastService: ToastService
  ) { }

  ngOnInit() {
  }

  // Metodo que realiza o Login
  realizarLogin() {
    // Colocando o spinner
    this.spinnerCarregar = true;

    // Realizando o login
    const loginRealizado = this.authService.realizarLogin(this.email, this.senha);

    setTimeout(() => {

      // Verificando
      if (loginRealizado) {
        // Toast
        this.toastService.toastSuccess('Usuário - Yuri Gabriel!', 'Bem-vindo ao Sistema Vasto Verde!');

        // Escondendo Spinner
        this.spinnerCarregar = false;

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
      1500);
  }

}
