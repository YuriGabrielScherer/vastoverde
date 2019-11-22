import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

import { CrudService } from '../shared/crud-service';
import { Atleta } from './../shared/model/atleta';
import { HttpClient } from '@angular/common/http';


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
