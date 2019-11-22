import { Component, OnInit } from '@angular/core';

import { AtletaService } from './../atleta.service';

@Component({
  selector: 'app-listar-atletas',
  templateUrl: './listar-atletas.component.html',
  styleUrls: ['./listar-atletas.component.scss']
})
export class ListarAtletasComponent implements OnInit {

  constructor(
    private atletaService: AtletaService
  ) { }

  ngOnInit() {
  }

}
