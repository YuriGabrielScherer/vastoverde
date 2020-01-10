import { ValidacoesFormService } from './../../shared/services/validacoes-form.service';
import { Component, OnInit, Input } from '@angular/core';

import { BsModalRef } from 'ngx-bootstrap/modal';
import { FormGroup } from '@angular/forms';
import { VwAtletaPessoa } from './../../shared/model/vwAtletaPessoa';

@Component({
  selector: 'app-atleta-alterar',
  templateUrl: './atleta-alterar.component.html',
  styleUrls: ['./atleta-alterar.component.scss'],
})
export class AtletaAlterarComponent implements OnInit {

  // Get nos dados do Atleta para mostrar no Modal
  @Input() atleta: VwAtletaPessoa;
  @Input() formulario: FormGroup;

  // Mascaras
  maskTelefone = ['(', /[1-9]/, /\d/, ')', /\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
  maskCpf = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];
  maskData = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/];


  constructor(
    public bsModalRef: BsModalRef,
    public validaForm: ValidacoesFormService
  ) { }

  ngOnInit() {
    this.onLoading();
  }

  onClose() {
    this.bsModalRef.hide();
  }

  onLoading() {



  }

  onClick() {
    console.log(this.atleta);
  }
}
