import { Atleta } from '../../shared/model/atleta';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Resolve } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AtletaResolver implements Resolve<Atleta> {

  private atleta: Atleta;

  constructor(
  ) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Atleta> | Atleta {

    return this.atleta;

  }
}
