import { take } from 'rxjs/operators';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Email } from './../model/email';
import { EnviaEmailComponent } from './envia-email.component';

@Injectable({providedIn: 'root'})
export class EnviaEmailService {

  constructor(
    private modalService: BsModalService,
    protected http: HttpClient,
  ) { }

  // Show Enviar E-mail Modal
  showModal() {
    const bsModalRef: BsModalRef = this.modalService.show(EnviaEmailComponent);

    return (bsModalRef.content as EnviaEmailComponent);
  }

  enviarEmail(email: Email) {
    console.log('Debug Enviar Email -> ', email);
    return this.http.post('http://localhost:8080/api/enviarEmail', email)
    .pipe(
      take(1)
    );
  }


}
