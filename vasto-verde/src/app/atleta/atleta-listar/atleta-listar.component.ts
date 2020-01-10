import { ToastService } from './../../shared/services/toast/toast.service';
import { AtletaAlterarService } from './../atleta-alterar/atleta-alterar.service';
import { ValidacoesFormService } from './../../shared/services/validacoes-form.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { Router } from '@angular/router';
import { Component, OnInit, TemplateRef } from '@angular/core';

import { AtletaService } from './../atleta.service';
import { VwAtletaPessoa } from './../../shared/model/vwAtletaPessoa';

@Component({
  selector: 'app-atleta-listar',
  templateUrl: './atleta-listar.component.html',
  styleUrls: ['./atleta-listar.component.scss'],
  preserveWhitespaces: true
})
export class AtletaListarComponent implements OnInit {

  // Listagem de Atletas cadastrados.
  atletas: VwAtletaPessoa[];



  // Objeto Campeonato
  campeonatos = [
    'Olesc',
    'Joguinhos',
    'Jasc',
    'Brasileiro'
  ];

  formulario: any;

  modalRef: BsModalRef;

  constructor(
    private atletaService: AtletaService,
    private modal: AtletaAlterarService,
    private toast: ToastService,
    protected validacaoForm: ValidacoesFormService
  ) { }

  ngOnInit() {

    console.log(this.atletas);

    this.carregarListaAtletas();

  }

  retornarAtletas() {
    return this.atletas;
  }

  openModal(idAtleta: number) {
    this.modal.showModal(idAtleta);
  }

  // Metodo para carregar a Lista de Atletas
  private carregarListaAtletas() {
    this.atletaService.getAtletasPessoas().subscribe(
      (success) => {
        this.atletas = success;
      },
      (error) => {
        switch (error['status']) {
          default: {
            this.toast.toastErroBanco();
            break;
          }
        }
      });
  }


}
