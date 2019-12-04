import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { CrudService } from './../shared/services/crud-service';
import { Atleta } from './../shared/model/atleta';

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

}
