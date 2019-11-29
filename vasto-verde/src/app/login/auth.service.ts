import { Pessoa } from '../shared/model/pessoa';
import { PessoaService } from '../atleta/pessoa.service';
import { ToastService } from '../shared/services/toast/toast.service';
import { Injectable, EventEmitter, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { EMPTY } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // Relação de Usuários
  private pessoaLogada: Pessoa;

  constructor(
    // Router para trocar as rotas
    private router: Router,
    private pessoaService: PessoaService,
    private toast: ToastService
  ) { }

  // Metodo realizar Login
  realizarLogin(login) {

    console.log(login);

    this.pessoaService.loadByEmail(login['email'])
      .pipe(
        // Tratamento de erros
        catchError((error) => {
          this.toast.toastError('Erro na conexão com o servidor.', 'Entre em contato com o administrador do sistema.');
          return EMPTY;
        })
      )
      .subscribe((response: Pessoa) => {

        // Puxando informacao do Servidor
        this.pessoaLogada = response[0];

        // Verificando o login
        if ((login['email'] === this.pessoaLogada.email) && (login['senha'] === this.pessoaLogada.senha)) {

          // Verificando lembrar de mim
          if (login['lembrar']) {
            // Setando no localStorage para controle do Site
            localStorage.setItem('usuario_logado', this.pessoaLogada.id.toString());
          } else {
            // Setando no sessionStorage para controle do Site
            sessionStorage.setItem('usuario_logado', this.pessoaLogada.id.toString());
          }
        }
      });
  }

  // Retornar Usuario autenticado ou nao
  usuarioAutenticado(): boolean {
    // Verificando se existe no sessionStorage
    if ((sessionStorage.getItem('usuario_logado')) || (localStorage.getItem('usuario_logado'))) {
      return true;
    }

    return false;
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
