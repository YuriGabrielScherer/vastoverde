import { Injectable, EventEmitter, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { EMPTY } from 'rxjs';

import { Pessoa } from '../shared/model/pessoa';
import { PessoaService } from '../pessoa/pessoa.service';
import { ToastService } from '../shared/services/toast/toast.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    // Router para trocar as rotas
    private router: Router,
    private pessoaService: PessoaService,
    private toast: ToastService
  ) { }

  // Metodo realizar Login
  realizarLogin(login) {

    console.log(login);

    this.pessoaService.login(login).subscribe((retorno) => {
      console.log(retorno);
    });

    // this.pessoaService.loadByEmail(login['email'])
    //   .pipe(
    //     // Tratamento de erros
    //     catchError((error) => {
    //       this.toast.toastError('Erro na conexão com o servidor.', 'Entre em contato com o administrador do sistema.');
    //       return EMPTY;
    //     })
    //   )
    //   .subscribe((response: Pessoa) => {

    //     setTimeout((dados) => {
    //       // Verificando o login
    //       if ((login['email'] === response.emailPessoa) && (login['senha'] === response.senhaPessoa)) {

    //         // Verificando lembrar de mim
    //         if (login['lembrar']) {
    //           // Setando no localStorage para controle do Site
    //           localStorage.setItem('usuario_logado', response.idPessoa.toString());
    //         } else {
    //           // Setando no sessionStorage para controle do Site
    //           sessionStorage.setItem('usuario_logado', response.idPessoa.toString());
    //         }
    //       }
    //     }, 2000);

    //   });
  }

  // Retornar Usuario autenticado ou nao
  usuarioAutenticado(): boolean {
    // Verificando se existe no sessionStorage
    if ((sessionStorage.getItem('usuario_logado')) || (localStorage.getItem('usuario_logado'))) {
      return true;
    }

    return false;
  }

  getIdAutenticado(): number {
    if (sessionStorage.getItem('usuario_logado')) {
      return sessionStorage.getItem('usuario_logado') as unknown as number;
    } else {
      return localStorage.getItem('usuario_logado') as unknown as number;
    }
  }

  // Deslogar
  deslogar() {
    // Removendo do Session Storage
    sessionStorage.removeItem('usuario_logado');

    // Removendo do Local Storage
    localStorage.removeItem('usuario_logado');

    // Rotacionando
    this.router.navigate(['/', 'login']);
  }
}
