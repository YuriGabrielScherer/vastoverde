import { Component, OnInit } from '@angular/core';

import { EnviaEmailService } from './../../shared/envia-email/envia-email.service';

@Component({
  selector: 'app-atleta-funcoes',
  templateUrl: './atleta-funcoes.component.html',
  styleUrls: ['./atleta-funcoes.component.scss']
})
export class AtletaFuncoesComponent implements OnInit {

  constructor(
    private enviaEmail: EnviaEmailService
  ) { }

  ngOnInit() {
  }

  onEnviaEmail() {
    this.enviaEmail.showModal();
  }

}
