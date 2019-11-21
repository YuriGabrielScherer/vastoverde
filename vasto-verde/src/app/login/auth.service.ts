import { ToastService } from './../shared/toast/toast.service';
import { Injectable, EventEmitter, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    // Router para trocar as rotas
    private router: Router
  ) { }

  // Metodo realizar Login
  realizarLogin(email, senha): boolean {

    // Validando Login
    if (email === 'yuri' && senha === '123') {

      // Guardando no LocalStorage
      localStorage.setItem('usuario_logado', 'yuri');
    }

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
