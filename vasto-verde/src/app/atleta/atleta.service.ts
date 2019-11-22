import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { delay, take } from 'rxjs/operators';

import { CrudService } from '../shared/crud-service';
import { Atleta } from './../shared/model/atleta';


@Injectable({
  providedIn: 'root'
})
export class AtletaService extends CrudService<Atleta> {

  constructor(
    protected http: HttpClient
  ) {

    // Construtor do CrudService
    super(http, `${environment.API}atleta/`);
  }

  //  Retornar o Atleta usando o e-mail
  loadByEmail(email: string) {
    return this.http.get<Atleta>(`${environment.API}atleta?email=${email}`)
      .pipe(
        delay(2000),
        take(1)
      );
  }

}
