import { Email } from './../model/email';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { Component, OnInit } from '@angular/core';

import { EnviaEmailService } from './envia-email.service';

@Component({
  selector: 'app-envia-email',
  templateUrl: './envia-email.component.html',
  styleUrls: ['./envia-email.component.scss']
})
export class EnviaEmailComponent implements OnInit {

  formulario: FormGroup;

  teste: [
    {nome: 'Yuri Gabriel S', email: 'yuri@email.com'},
    {nome: 'Marco Antonio', email: 'marco@email.com'},
    {nome: 'Veroni Pereira', email: 'veroni@email.com'},
    {nome: 'Teste Unitario', email: 'teste@email.com'},
  ];

  constructor(
    public bsModalRef: BsModalRef,
    private formBuilder: FormBuilder,
    private emailService: EnviaEmailService
  ) { }

  ngOnInit() {
    this.criarFormulario();
  }

  private onClose() {
    this.bsModalRef.hide();
  }

  private criarFormulario() {
    this.formulario = this.formBuilder.group({
      email: [null],
      assunto: [null, Validators.required],
      mensagem: [null, [Validators.required, Validators.maxLength(250)]]
    });

  }

  private enviarEmail() {

    const email: Email = new Email();

    email.remetente = 'karatevastoverde@gmail.com';
    email.destinatario = 'yurisvaz@hotmail.com';
    email.assunto = 'Testando Aplicaçao Web do Yuri';
    email.mensagem = 'Oi, gatinho \n\n Estou Testando minha aplicaçao Web Marcos!!';

    this.emailService.enviarEmail(email).subscribe(
      (success) =>{
        console.log(success);
      }, (erro) =>{
        console.log(erro)
      }, () => console.log('Debug envia email component finally')
      );
  }

}
