import { Atleta } from './../shared/model/atleta';
import { AtletaService } from './../atleta/atleta.service';
import { ToastService } from './../shared/toast/toast.service';
import { Injectable, EventEmitter, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { EMPTY } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // Usuario que fez o login
  private atleta: Atleta;

  constructor(
    // Router para trocar as rotas
    private router: Router,
    private atletaService: AtletaService,
    private toast: ToastService
  ) { }

  // Metodo realizar Login
  realizarLogin(email, senha): boolean {

    this.atletaService.loadByEmail(email)
      .pipe(
        catchError((error) => {
          this.toast.toastError('Erro ao realizar o login.', 'Erro ao realizar o login. Tente novamente mais tarde.');
          return EMPTY;
        })
      )
      .subscribe((response: Atleta) => {

        console.log('Resposta : ' + response.nome);

        // Passando as informacoes
        this.atleta = response;

        console.log('Dentro ' + this.atleta.email + this.atleta.senha);

      });

    console.log('Fora ' + this.atleta);

    return this.usuarioAutenticado();
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
