
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { Atleta } from './../../shared/model/atleta';
import { AtletaService } from './../atleta.service';

import { Pessoa } from './../../shared/model/pessoa';
import { PessoaService } from './../../pessoa/pessoa.service';

@Component({
  selector: 'app-atleta-listar',
  templateUrl: './atleta-listar.component.html',
  styleUrls: ['./atleta-listar.component.scss'],
  preserveWhitespaces: true
})
export class AtletaListarComponent implements OnInit {

  // Listagem de Atletas cadastrados.
  atletas: Atleta[];

  // Objeto Campeonato
  campeonatos = [
    'Olesc',
    'Joguinhos',
    'Jasc',
    'Brasileiro'
  ];

  constructor(
    private atletaService: AtletaService
  ) { }

  ngOnInit() {

  }

  retornarAtletas() {


    setTimeout(() => {// Retornando os atletas
      this.atletas = this.atletaService.getAtletas();
    }, 2000);
    return this.atletas;
  }



}
