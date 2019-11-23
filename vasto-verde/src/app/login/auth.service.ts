import { Atleta } from './../shared/model/atleta';
import { AtletaService } from './../atleta/atleta.service';
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
  private atletaLogado: Atleta;

  constructor(
    // Router para trocar as rotas
    private router: Router,
    private atletaService: AtletaService,
    private toast: ToastService
  ) { }

  // Metodo realizar Login
  realizarLogin(email, senha) {

    this.atletaService.loadByEmail(email)
      .pipe(
        // Tratamento de erros
        catchError((error) => {
          this.toast.toastError('Erro na conexão com o servidor.', 'Entre em contato com o administrador do sistema.');
          return EMPTY;
        })
      )
      .subscribe((response: Atleta) => {

        // Puxando informacao
        this.atletaLogado = response[0];

        // Verificando o login
        if ((email === this.atletaLogado.email) && (senha === this.atletaLogado.senha)) {
          // Setando no LocalStorage para controle do Site
          localStorage.setItem('usuario_logado', this.atletaLogado.id.toString());
        }
      });
  }

  // Retornar Usuario autenticado ou nao
  usuarioAutenticado(): boolean {

    // Verificando se existe no LocalStorage
    if (localStorage.getItem('usuario_logado')) {
      return true;
    }

    return false;
  }

  // Deslogar
  deslogar() {
    // Removendo do Session Storage
    localStorage.removeItem('usuario_logado');

    // Rotacionando
    this.router.navigate(['/', 'login']);
  }
}
