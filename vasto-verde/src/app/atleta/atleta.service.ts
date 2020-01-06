import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudService } from './../shared/services/crud-service';
import { Atleta } from './../shared/model/atleta';

@Injectable({
  providedIn: 'root'
})
export class AtletaService extends CrudService<Atleta> {

  atletas: Atleta[] = [
    // {
    //   idPessoa: 1, dataInicio: "05/09/2014", idGrau: 1, federacao: 2544, confederacao: 9966
    // },
    // { idPessoa: 2, dataInicio: "05/09/2014", idGrau: 2, federacao: 2544, confederacao: 9966 },
    // { idPessoa: 3, dataInicio: "05/09/2014", idGrau: 3, federacao: 2544, confederacao: 9966 },
    // { idPessoa: 4, dataInicio: "05/09/2014", idGrau: 4, federacao: 2544, confederacao: 9966 },
    // { idPessoa: 5, dataInicio: "05/09/2014", idGrau: 5, federacao: 2544, confederacao: 9966 },
  ];

  constructor(
    protected http: HttpClient
  ) {

    // Construtor do CrudService
    super(http, `${environment.API}atleta`);
  }

  getAtletas() {
    return this.atletas;
  }



}
