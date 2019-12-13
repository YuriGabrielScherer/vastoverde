import { Component, OnInit, OnDestroy } from '@angular/core';

import { AuthService } from './../../login/auth.service';
import { PessoaService } from './../../pessoa/pessoa.service';
import { Pessoa } from './../../shared/model/pessoa';
import { Title } from '@angular/platform-browser';


@Component({
  selector: 'app-administrativo',
  templateUrl: './administrativo.component.html',
  styleUrls: ['./administrativo.component.scss']
})
export class AdministrativoComponent implements OnInit {

  // Usuario autenticado
  usuarioAutenticado: Pessoa = new Pessoa();


  constructor(
    private pessoaService: PessoaService,
    private authService: AuthService,
    private title: Title
  ) { }

  ngOnInit() {
    this.title.setTitle('Administrativo Karatê');
    // this.usuarioAutenticado = this.authService.getUsuarioAutenticado();
  }



}
