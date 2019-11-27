import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { delay, take } from 'rxjs/operators';

import { CrudService } from '../shared/services/crud-service';
import { Pessoa } from '../shared/model/pessoa';


@Injectable({
  providedIn: 'root'
})
export class PessoaService extends CrudService<Pessoa> {


  constructor(
    protected http: HttpClient
  ) {

  // Construtor do CrudService
  super(http, `${environment.API}pessoa`);
}

//  Retornar o Pessoa usando o e-mail
loadByEmail(email: string) {

  return this.http.get<Pessoa>(`${environment.API}pessoa?email=${email}`)
    .pipe(
      delay(1300),
      take(1)
    );
}

}
