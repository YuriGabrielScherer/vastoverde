import { Injectable, EventEmitter, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginValidado: boolean;

  constructor(
    // Router para trocar as rotas
    private router: Router
  ) { }



  // Metodo realizar Login
  realizarLogin(email, senha) {

    if (email === 'yuri' && senha === '123') {


      this.loginValidado = true;

      // Redirecionando
      setTimeout(alert => this.router.navigate(['/administrativo'])
        , 3000);


    } else {

      this.loginValidado = false;
    }

  }

  // Retornar Usuario autenticado ou nao
  usuarioAutenticado(): boolean {
    console.log('login ok ' + this.loginValidado);
    return this.loginValidado;
  }
}
