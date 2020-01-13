import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { Pessoa } from '../shared/model/pessoa';
import { PessoaService } from './../pessoa/pessoa.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private usuarioLogado: Pessoa = null;

  constructor(
    private router: Router,
    private pessoaService: PessoaService
  ) { }

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

  setUsuarioLogado(pessoa: Pessoa) {
    this.usuarioLogado = pessoa;
  }

  getUsuarioAutenticado() {

    let idPessoa;

    if (sessionStorage.getItem('usuario_logado')) {
      idPessoa = sessionStorage.getItem('usuario_logado') as unknown as number;
    } else {
      idPessoa = localStorage.getItem('usuario_logado') as unknown as number;
    }

    this.pessoaService.loadById(idPessoa).subscribe(
      (success: Pessoa) => {

        this.usuarioLogado = success;

        return success;
      }, (erro) => {
        console.log('Degub Administrativo Service -> Erro ao retornar usuário.');
      });
  }
}
