import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { take, delay } from 'rxjs/operators';

import { VwAtletaPessoa } from './../shared/model/vwAtletaPessoa';
import { CrudService } from './../shared/services/crud-service';
import { Atleta } from './../shared/model/atleta';

import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AtletaService extends CrudService<Atleta> {

  constructor(
    protected http: HttpClient
  ) {

    // Construtor do CrudService
    super(http, `${environment.API}atleta`);
  }

  getAtletasPessoas() {
    return this.http.get<VwAtletaPessoa[]>(`${environment.API}atleta`)
      .pipe(
        take(1)
      );
  }

  getAtletaPessoaById(idAtleta: number) {
    return this.http.get<VwAtletaPessoa>(`${environment.API}atleta/${idAtleta}`)
      .pipe(
        delay(1000),
        take(1)
      );
  }





}
