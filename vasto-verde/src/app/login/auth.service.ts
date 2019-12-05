import { Injectable, EventEmitter, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { EMPTY, Observable } from 'rxjs';

import { Pessoa } from '../shared/model/pessoa';
import { PessoaService } from '../pessoa/pessoa.service';
import { ToastService } from '../shared/services/toast/toast.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private router: Router,
  ) { }

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
