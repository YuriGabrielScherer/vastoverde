import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { AuthService } from './../../login/auth.service';
import { PessoaService } from './../../pessoa/pessoa.service';
import { Pessoa } from './../../shared/model/pessoa';


@Component({
  selector: 'app-administrativo',
  templateUrl: './administrativo.component.html',
  styleUrls: ['./administrativo.component.scss']
})
export class AdministrativoComponent implements OnInit, OnDestroy {

  // Var Inscricao
  private inscricao: Subscription;

  // Usuario autenticado
  usuarioAutenticado: Pessoa = new Pessoa();


  constructor(
    private pessoaService: PessoaService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    // Pegando usuario logado
    this.inscricao = this.pessoaService.loadById(this.authService.getIdAutenticado()).subscribe((usuario) => {
      this.usuarioAutenticado = usuario;
    });

  }

  ngOnDestroy() {
    this.inscricao.unsubscribe();
  }

}
