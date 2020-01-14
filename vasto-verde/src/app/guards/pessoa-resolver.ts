import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

import { Pessoa } from './../shared/model/pessoa';
import { PessoaService } from './../pessoa/pessoa.service';

@Injectable(
  { providedIn: 'root' }
)
export class PessoaResolver implements Resolve<Pessoa> {



  constructor(
    private pessoaService: PessoaService
  ) {

  }
  resolve(route: ActivatedRouteSnapshot): Observable<Pessoa> | Promise<Pessoa> | Pessoa {

    let idPessoa;

    if (sessionStorage.getItem('usuario_logado')) {
      idPessoa = sessionStorage.getItem('usuario_logado') as unknown as number;
    } else {

      idPessoa = localStorage.getItem('usuario_logado') as unknown as number;
    }

    return this.pessoaService.loadById(idPessoa);
  }
}
